package com.erji.nsu.lab4.threadPool;

import java.util.LinkedList;

public class PooledThread extends Thread {

    private final LinkedList<ThreadPoolTask> taskQueue;

    public PooledThread(String name, LinkedList<ThreadPoolTask> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    private void performTask(ThreadPoolTask task) {
        try {
            task.go();
        } catch (InterruptedException ex) {
        }
    }

    public void run() {
        ThreadPoolTask toExecute;
        while (true) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException ex) {
                    }
                    continue;
                } else {
                    toExecute = taskQueue.pop();
                }
            }
            performTask(toExecute);
        }
    }
}