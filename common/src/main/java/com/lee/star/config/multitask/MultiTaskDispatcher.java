package com.lee.star.config.multitask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lidanfeng
 * @date 2019/5/13 15:06
 * @description 多线程并行任务调度器
 */
@Component
public class MultiTaskDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(MultiTaskDispatcher.class);

    //线程池
    private ThreadPoolExecutor pool = null;
    //初始化锁
    private ReentrantLock lock = new ReentrantLock();
    //任务优先级比较器
    private Comparator taskUnitComparator = new Comparator<TaskUnit>() {
        @Override
        public int compare(TaskUnit t1, TaskUnit t2) {
            return t1.getPriorityOfQueue() - t2.getPriorityOfQueue();
        }
    };

    @PostConstruct
    public void initPool() {
        pool = new ThreadPoolExecutor(20, 200, 5,
                TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * @param tasks
     */
    public void runTasks(List<TaskUnit> tasks) {
        if (pool == null) {
            lock.lock();
            try {
                initPool();
            } finally {
                lock.unlock();
            }
        }
        //组织并对任务排队
        Collections.sort(tasks, taskUnitComparator);
        Integer currentPriority = null;
        //当前执行多线程任务集合
        Set<TaskUnit> batchSet = new HashSet<TaskUnit>();
        for (TaskUnit unit : tasks) {
            if (currentPriority == null) {
                currentPriority = unit.getPriorityOfQueue();
            }
            if (currentPriority == unit.getPriorityOfQueue()) {
                //同一优先级任务
                batchSet.add(unit);
            } else {
                //当前任务优先级较低,执行上一批高优先级任务
                if (!batchSet.isEmpty()) {
                    executeBatchTasks(batchSet);
                }
                batchSet.clear();
                currentPriority = unit.getPriorityOfQueue();
                batchSet.add(unit);
            }
        }
        if (!batchSet.isEmpty()) {
            executeBatchTasks(batchSet);
        }
    }

    /**
     * 执行一批任务，本批任务优先级相同
     *
     * @param set
     */
    private void executeBatchTasks(Set<TaskUnit> set) {
        try {
            CountDownLatch endSignal = new CountDownLatch(set.size());
            ThreadLocal<Object> threadLocal = ThreadContainer.getMyThreadLocal("innerSerialThreadLocal");
            Object threadParam = null;
            if (threadLocal != null) {
                threadParam = threadLocal.get();
            }
            for (TaskUnit task : set) {
                pool.execute(new RunnableTask(task, endSignal, threadParam));
            }
            if (ThreadContainer.getMyThreadLocal("innerSerialThreadLocal") != null) {
                threadLocal.set(ThreadContainer.getMyThreadLocal("innerSerialThreadLocal").get());
            }
            endSignal.await();
        } catch (Exception e) {
            logger.error("执行批量任务出错:", e);
            return;
        }
    }

    class RunnableTask implements Runnable {
        TaskUnit task;
        CountDownLatch end;
        Object threadParam = null;

        RunnableTask(TaskUnit task, CountDownLatch end) {
            this.task = task;
            this.end = end;
        }

        RunnableTask(TaskUnit task, CountDownLatch end, Object threadParam) {
            this.task = task;
            this.end = end;
            this.threadParam = threadParam;
        }

        @Override
        public void run() {
            try {
                if (threadParam != null &&
                        ThreadContainer.getMyThreadLocal("innerSerialThreadLocal") != null) {
                    ThreadContainer.getMyThreadLocal("innerSerialThreadLocal").set(this.threadParam);
                }
                task.execute();
            } catch (Exception e) {
                logger.error("子任务出错:{}", e);
            } finally {
                end.countDown();
                if (threadParam != null &&
                        ThreadContainer.getMyThreadLocal("innerSerialThreadLocal") != null) {
                    ThreadContainer.getMyThreadLocal("innerSerialThreadLocal").remove();
                }
            }
        }
    }
}
