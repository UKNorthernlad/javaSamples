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

public class ManualCompletableFuture {

    public void run() throws InterruptedException, ExecutionException, CancellationException{
   
    System.out.println("Basic Example using CompletableFuture");

    // Create a place to hold the result of the task.
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    // The Future object is not yet complete, yet you might still want to check if it is done.
    
    Boolean isDone = completableFuture.isDone();
    System.out.println("isDone(): " + isDone);

    String getNowResult  = completableFuture.getNow("some tempary data"); // default data to return if the task if isDone() == false.
    System.out.println("Get Now Result: " + getNowResult);

    // Business logic to decide if we need to continue waiting for the task to complete.
    //if(CheckForDataInCache() == true) { completableFuture.cancel(true); } // this would work
    if(CheckForDataInCache() == true) { completableFuture.complete("some data"); } // .complete() marks the task as done but let's you set the result.

    String result  = completableFuture.get(); // This will block until the task is done.
    System.out.println("Result: " + result);

    isDone = completableFuture.isDone();
    System.out.println("isDone(): " + isDone);

    getNowResult  = completableFuture.getNow("some tempary data"); // Will return "some data" because the task is done.
    System.out.println("Get Now Result: " + getNowResult);
}

public static boolean CheckForDataInCache() {
    // Do important checking stuff here.....
    return true;
}   
    
}

