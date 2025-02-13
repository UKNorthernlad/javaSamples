package com.mycompany.app;

//import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
//import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;

public class CompletableFuturesApp {
    public static void main(String[] args) throws InterruptedException, ExecutionException, CancellationException {

        // BasicExample basicExample = new BasicExample();
        // basicExample.run();

        // CombinationExample combinationExample = new CombinationExample();
        // combinationExample.run();

        CombinationExampleAsync combinationExampleAsync = new CombinationExampleAsync();
        combinationExampleAsync.run();
    }
           
}
