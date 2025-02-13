package com.mycompany.app;

//import java.util.concurrent.Callable;
//import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;

public class CombinationExampleAsync {
    public void run()  {
        System.out.println("Example with two CompletableFutures that are combined:");


        // Notice there is no need to create Threads using the ExecutorService class when using CompletableFutures.


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("future1 is now returning....");
            return "Hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 is now returning....");
            return "World";
        });

        // s1 and s2 are the results of the two CompletableFutures.
        CompletableFuture<String> futureCombined = future1.thenCombineAsync(future2, (s1, s2) -> {
            System.out.println("Combining the results of future1 and future2....");
            return s1 + " " + s2;
        });

        // .thenAccept() is used when you don't need to return a value.
        futureCombined.thenAccept(System.out::println);

        // Do some other work in the main thead.

        // Now block and wait for all the futures to complete.
        try {
            System.out.println("Result: " + futureCombined.get());
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("All done.");
        
    }    
}
