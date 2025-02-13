package com.mycompany.app;

//import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;

public class BasicExample {

    public void run() throws InterruptedException, ExecutionException, CancellationException{
   
    System.out.println("Basic Example using CompletableFuture");

    // Create a place to hold the result of the task.
    CompletableFuture<Integer> completableFuture = new CompletableFuture<>();

    // Define a task to run.
    // Callable<Integer> task = () -> {
    //     try {
    //         // Do useful work here like reading data from a database.
    //         Thread.sleep(100000);
    //         return 42;
    //     } catch (InterruptedException ex) {
    //         throw new RuntimeException(ex);
    //     }
    // };

    // Notice we have not actually assigned the task or started it on the CompletableFuture.
    
    // Here we manually check the task to see if it is done.
    Boolean isDone = completableFuture.isDone();
    System.out.println("isDone(): " + isDone);

    // This is like peeking at the result of the task without blocking.
    // It contains default data to return if the task if isDone() == false.
    Integer getNowResult  = completableFuture.getNow(1);
    System.out.println("Get Now Result: " + getNowResult);

    // Business logic to decide if we need to continue waiting for the task to complete.
    //if(CheckForDataInCache() == true) { completableFuture.cancel(true); } // this would work
    if(CheckForDataInCache() == true) { completableFuture.complete(2); } // .complete() marks the task as done but let's you set the result.

    Integer result  = completableFuture.get(); // This won't block now because the task is already marked as done.
    System.out.println("Result: " + result);

    isDone = completableFuture.isDone();
    System.out.println("isDone(): " + isDone);

    // Calling getNow() again will return the result of the task because it is done and not the default data.
    getNowResult  = completableFuture.getNow(1); // Will return "some data" because the task is done.
    System.out.println("Get Now Result: " + getNowResult);
}

public static boolean CheckForDataInCache() {
    // Do important checking stuff here.....
    return true;
}   
    
}

