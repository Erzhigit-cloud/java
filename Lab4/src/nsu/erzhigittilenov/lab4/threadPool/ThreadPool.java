package nsu.antonsokovnin.lab4.threadPool;
import java.util.*;

public class ThreadPool  {
   private final LinkedList<ThreadPoolTask> taskQueue = new LinkedList<>();

   public void addTask(Task t) {
      synchronized (taskQueue) {
         taskQueue.add(new ThreadPoolTask(t));
         taskQueue.notify();
      }
   }

   public ThreadPool(Integer noOfThreads) {
      HashSet<PooledThread> availableThreads = new HashSet<>();
      for (int i = 0; i < noOfThreads; i++) {
         availableThreads.add(new PooledThread(Integer.toString(i), taskQueue));
      }
      for (var thread : availableThreads) {
         thread.start();
      }
   }
}