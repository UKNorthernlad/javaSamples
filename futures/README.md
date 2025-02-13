# Futures and multi-threaded programming

> All examples are based on those from https://github.com/dosomedev/java/

This is a "all-in-one" example of the first 4 examples from https://github.com/dosomedev/java/tree/main/Future/future-app/src/main/java/com/dosomedev

These are all blocking examples, i.e. the main thread will block and wait for the task in the new thread to complete. See example 5 at the above address for a non-blocking example which uses Future.isDone() to allow checking in a loop to see if the task has completed.

## What are Futures?

A future is an object that will hold the result of some work that is yet to be performed (perhaps even yet to be defined). Think of it as a "inbox" that is currently empty, but shortly you will ask somebody to do some work for you and when complete, they will put the results into the inbox.

You can sit and wait for the results to arrive (known as a blocking call) or check-in with the inbox every of often to see if any results are available (by checking a property called .isDone()).

The threads that perform the required tasks don't magically pop into existance. You must create these using an *ExecutorService* which manages a pool of free threads. A task is then assigned to a free thread. 

In practice this allows you to perform other tasks on other threads leaving the main thread unblocked. E.g. Running a query on a database and waiting for the results to arrive back.

Introduced in Java 5, these where the first? tools available to aid multi-threaded programming.