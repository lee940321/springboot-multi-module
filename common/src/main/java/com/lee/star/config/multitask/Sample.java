package com.lee.star.config.multitask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Sample {
    public static void main(String[] args) {
        MultiTaskDispatcher multiTaskDispatcher = new MultiTaskDispatcher();
        List<TaskUnit> list = new ArrayList<>();
        int count = 1;
        list.add(new DemoTask(count++));
        list.add(new DemoTask(count++));
        list.add(new DemoTask(count++));
        list.add(new DemoTask(count++));
        list.add(new DemoTask(count++));
        list.add(new DemoTask(count++));
        list.add(new DemoTask(count++));
        multiTaskDispatcher.runTasks(list);
        System.out.println("all tasks done");
    }
}


class DemoTask implements TaskUnit {

    private final int i;

    DemoTask(int i) {
        this.i = i;
    }

    @Override
    public void execute() {
        try {
            Thread.sleep(new Random().nextInt(3) * 1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + i + " finishied");
    }

    @Override
    public int getPriorityOfQueue() {
        return 0;
    }
}
