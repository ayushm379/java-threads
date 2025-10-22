package io.at.learn.thread01;

import java.util.List;
import java.util.concurrent.*;

public class Thread02 {

    private static final Runnable runnable = () -> {
        Thread currentThread = Thread.currentThread();
        System.out.println("Runnable " + currentThread.getId() + " - " + currentThread.getName());
    };

    private static final Callable<String> callable = () -> {
        Thread currentThread = Thread.currentThread();
        System.out.println("Callable " + currentThread.getId() + " - " + currentThread.getName());
        return currentThread.getId() + " - " + currentThread.getName();
    };


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<?> runnableResponse =  executor.submit(runnable);
        Future<String> callableResponse = executor.submit(callable);
        executor.invokeAny(List.of(callable, callable));
        executor.submit(runnable);
        executor.submit(runnable);

        System.out.println("Runnable Response -> " + runnableResponse.get());
        System.out.println("Callable Response -> " + callableResponse.get());
        executor.shutdown();
    }

}
