package com.lee.star.config.multitask;

/**
 * 工作单元
 * @author lidanfeng
 */
public interface TaskUnit {

    /**
     * 执行逻辑
     *
     * @return
     */
    void execute();

    /**
     * 返回任务在队列中的执行优先级，如任务1(w1)的优先级是1，任务2(w2)和任务3(w3)的优先级都是2
     * 那么首先执行w1,完成后并行执行w2和w3,全部完成后返回
     *
     * @return
     */
    int getPriorityOfQueue();
}
