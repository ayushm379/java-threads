package io.at.learn.thread03;

import java.util.concurrent.*;

public class Thread01 {

    private static final int MAX_RETRY = 10;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Future
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future1 = executorService.submit(() -> {
            Thread.sleep(2000);
            return "Hello";
        });

        Future<String> future2 = executorService.submit(() -> {
            Thread.sleep(3000);
            return "World";
        });

        int retry = 0;
        while(!(future1.isDone() && future2.isDone()) && retry++ < MAX_RETRY) {
            System.out.println("TASKs NOT EXECUTED, THREAD IS GOING TO SLEEP");
            Thread.sleep(2000);
        }

        String message1 = future1.get();
        String message2 = future2.get();
        System.out.println("Future COMBINED RESULTS: " + message1 + " + " + message2);
        executorService.shutdown();

        // Completable future
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> cFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "World";
        }, executor);

        CompletableFuture<String> cFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        }, executor);

        CompletableFuture<Object> result = cFuture1.thenCombine(cFuture2, (result1, result2) -> result1 + " + " + result2);
        CompletableFuture<String> modifiedRes = result.thenApplyAsync(res -> {
            System.out.println("CFuture COMBINED RESULTS: " + res);
            return "Modified " + res;
        });
        modifiedRes.thenRun(() -> System.out.println("Running after modification..."));

        System.out.println("CFuture COMBINED RESULTS: " + result.get());
        System.out.println("CFuture MODIFIED RESULT: " + modifiedRes.get());
        executor.shutdown();
    }

}
