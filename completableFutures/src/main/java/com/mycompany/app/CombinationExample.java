package com.mycompany.app;

//import java.util.concurrent.Callable;
//import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;

public class CombinationExample {
    public void run()  {
        System.out.println("Example with two CompletableFutures that are combined:");


        // Notice there is no need to create Threads using the ExecutorService class when using CompletableFutures.


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "World";
        });

        // s1 and s2 are the results of the two CompletableFutures.
        CompletableFuture<String> futureCombined = future1.thenCombine(future2, (s1, s2) -> {
            return s1 + " " + s2;
        });

        futureCombined.thenAccept(System.out::println);
    }    
}
