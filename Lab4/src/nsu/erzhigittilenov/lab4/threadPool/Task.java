package nsu.antonsokovnin.lab4.threadPool;

public interface Task {
   String getName();
   void performWork() throws InterruptedException;
}