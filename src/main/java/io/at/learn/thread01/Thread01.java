package io.at.learn.thread01;

import java.util.List;

public class Thread01 {

    private static final Runnable runnable = () -> {
        Thread currentThread = Thread.currentThread();
        System.out.println("Runnable Lambda : " + currentThread.getId() + " - " + currentThread.getName());
    };

    private static class MyRunnable implements Runnable {
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println("Runnable Class : " + currentThread.getId() + " - " + currentThread.getName());
        }
    }

    private static class MyThread extends Thread {
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println("Thread Class : " + currentThread.getId() + " - " + currentThread.getName());
        }
    }

    private static Thread createThread(int type) {
        return switch (type) {
            case 1 -> new Thread(runnable);
            case 2 -> new Thread(new MyRunnable());
            case 3 -> new MyThread();
            default -> throw new RuntimeException("Invalid input");
        };
    }

    public static void main(String[] args) {
        List.of(createThread(1),
                createThread(3),
                createThread(2),
                createThread(2),
                createThread(3),
                createThread(1))
                .forEach(Thread::start);
    }

}
