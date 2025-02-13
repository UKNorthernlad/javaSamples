package com.mycompany.app;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Futures {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello World!");

        // Create a new thread on which to run the task.
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Define the task to be run on the new thread.
        Callable<Integer> task = () -> {
            // Perform some computation
            int x =1 ;
            try {
                Thread.sleep(10000);
                //x = x-1;
                int y = 42/x;
                System.out.println("Task now returning a value...."); 
                return y;
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Exception Handler."); 
                System.out.println("Value of x = " + x); 
                System.out.println(ex.getMessage()); 
                throw new RuntimeException("Interrupted", ex.getCause());
            } catch (Exception ex) {
                System.out.println("Exception Handler."); 
                System.out.println("Value of x = " + x); 
                System.out.println(ex.getMessage()); 
                throw new RuntimeException("Exception", ex.getCause());
            }
        };

        // Start the task running.
        Future<Integer> future = executor.submit(task);


        // Uncomment either of the two sections below to see the effect of cancelling the task.
        // ****************************************************************************************************
        // // Calls to .sleep() are normally wrapped in a try-catch block because they can throw an exception.
        // // In this case, the main() has a throws InterruptedException clause so we don't need to catch it.
        // Thread.sleep(1000);

        // // Sleep for a while on the main thread and then cancel the task.
        // try {
        //     Thread.sleep(1000);
        //     System.out.println("Cancelling the task.");
        //     future.cancel(true);
        // } catch (InterruptedException ex) {
        //     System.err.println("Interupted exception: " + ex.getMessage());
        // }   
        // ****************************************************************************************************


        // // Here we create a new Thread (pretend it's doing something important) but then it decides it needs to cancel the task.
        // // ****************************************************************************************************
        // new Thread(() -> {
        //     try {
        //         Thread.sleep(2000);
        //         System.out.println("Cancelling the task.");
        //         future.cancel(true);
        //     } catch (InterruptedException ex) {
        //         System.err.println("Interupted exception: " + ex.getMessage());
        //     }
        // }).start();
        // // ****************************************************************************************************

        // The call to .get() must be wrapped in a try-catch block because it can throw an exception.
        try {
            //Integer result = future.get(); // This will block the main thread until the task is complete.
            Integer result = future.get(3000, TimeUnit.MILLISECONDS); // This will block the main thread but only for a short time.
            System.out.println("Result: " + result);
        } catch (ExecutionException ex) { // 
            System.err.println("Execution exception: " + ex.getMessage());
        } catch (InterruptedException ex) { // 
            System.err.println("Interupted exception: " + ex.getMessage());
        } catch (TimeoutException ex) { // 
            System.err.println("TimeoutExecution exception: " + ex.getMessage());
            // A timeout exception does not cancel the task. It just means the task did not complete in time.
            // Manuallu cancel the task.
            future.cancel(true);
        } finally {
            // Shutdown the executor or program will not finish as the thread is still running.
            executor.shutdown();
        }

        System.out.println("Main thread end.");
    }
}
