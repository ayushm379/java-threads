package io.at.learn.thread03;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Thread02 {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
        var ref = new Object() {
            Integer counter = 0;
        };

        Runnable runnable = () -> {
            for(int i=0; i<5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int currentValue = counter.incrementAndGet();
                ref.counter = ref.counter + 1;
                System.out.println(Thread.currentThread().getName() + " INCREMENTED TO " + currentValue + " : " + ref.counter);
            }
        };

        List.of(
                    new Thread(runnable),
                    new Thread(runnable),
                    new Thread(runnable),
                    new Thread(runnable))
                .forEach(Thread::start);
    }

}
