package io.at.learn.thread02;

public class Thread01 {

    private static class ResultHolder {

        private String message;

        public synchronized void setMessage(String message) {
            Thread currentTread = Thread.currentThread();
            this.message = message;
            System.out.println("THREAD " + currentTread.getName() + " WAITING!!!");
            notify();
        }

        public synchronized String waitForResult() throws InterruptedException {
            Thread currentTread = Thread.currentThread();
            while(message == null) {
                System.out.println("THREAD " + currentTread.getName() + " WAITING!!!");
                // WAITING STATE
                wait();
            }
            System.out.println("THREAD " + currentTread.getName() + " STARTING RUNNING AGAIN!!!");
            return message;
        }

    }

    private record ProducerRunnable(ResultHolder resultHolder) implements Runnable {

        @Override
            public void run() {
                String message = "HELLO FROM PRODUCER!";
                System.out.println("SENDING MESSAGE: " + message);
                resultHolder.setMessage(message);
            }

        }

    private record ConsumerRunnable(ResultHolder resultHolder) implements Runnable {

        @Override
            public void run() {
                try {
                    String message = resultHolder.waitForResult();
                    System.out.println("RECEIVED MESSAGE : " + message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    public static void main(String[] args) throws InterruptedException {
        ResultHolder resultHolder = new ResultHolder();

        // NEW STATE
        Thread producer = new Thread(new ProducerRunnable(resultHolder));
        Thread consumer = new Thread(new ConsumerRunnable(resultHolder));

        // RUNNING STATE
        producer.start();
        consumer.start();

        // WAITING STATE
        producer.join();
        consumer.join();

        // TERMINATED STATE
    }

}
