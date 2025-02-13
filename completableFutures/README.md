# Completable Futures

> All examples are based on those from https://github.com/dosomedev/java/

These examples are based on those from https://github.com/dosomedev/java/tree/main/CompletableFuture/completablefuture-app/src/main/java/com/dosomedev

> Good reference video: https://www.youtube.com/watch?v=xpjvY45Hbyg

## Futures v Completable Futures

Futures where a step forward in creating multi-threaded asynchronous applications however they had some limitations. These are addressed in a known as *Completable Futures*.

The main limiations Futures that are overcome in CompletablFutures are:

### 1 - Futures cannot be completed manually ###
They can be cancelled, but not manually marked as completed with a given status.

Manually marking a Future as complete would allow it to appear like it has returned from it's task, but instead you can supply your own return value. 

### 2 - Actions cannot be performed until the result is available ###
XXX

### 3 - Attaching a callback function is not possible ###
No ability to have a callback called automatically when the Future result is available.

### 4 - Multiple Futures cannot be chained together ###
XXX

### 5 - Multiple Futures cannot be combined together ###
There is no way to run multiple Futures in parallel then run some function after all of them are complete.

### 6 - No Exception Handling in the Future API ###
XXX