package com.erji.nsu.lab4.threadPool;
import java.util.*;

public class ThreadPool  {

   private final LinkedList<ThreadPoolTask> taskQueue = new LinkedList<>();

   public ThreadPool(Integer noOfThreads) {

      HashSet<PooledThread> availableThreads = new HashSet<>();

      for (int i = 0; i < noOfThreads; i++) {
         availableThreads.add(new PooledThread(Integer.toString(i), taskQueue));
      }

      for (var thread : availableThreads) {
         thread.start();
      }

   }

   public void addTask(Task t) {
      synchronized (taskQueue) {
         taskQueue.add(new ThreadPoolTask(t));
         taskQueue.notify();
      }
   }

}