package io.at.learn.thread03;

import java.util.*;

public class Thread03 {

    private static final int TOTAL_THREADS = 50;
    private static final int ELEMENTS_PER_THREAD = 1500;
    private static final int MAX_VALUE_ALLOWED = 500000;

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());
        List<Integer> list2 = new ArrayList<>();
        Random random = new Random();

        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<TOTAL_THREADS; i++) {
            threads.add(new Thread(() -> {
                for(int j=0; j<ELEMENTS_PER_THREAD; j++) {
                    int number = random.nextInt(MAX_VALUE_ALLOWED);
                    list1.add(number);
                    list2.add(number);
                }
            }));
        }

        threads.forEach(Thread::start);
        for(Thread thread: threads) thread.join();

        System.out.println("Size of List 1 " + list1.size());
        System.out.println("Size of List 2 " + list2.size());
    }

}
