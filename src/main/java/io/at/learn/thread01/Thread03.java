package io.at.learn.thread01;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Thread03 {

    static class FiboTask extends RecursiveTask<Long> {

        private final Long number;

        public FiboTask(Long number) {
            this.number = number;
        }

        @Override
        protected Long compute() {
            if(number == 0 || number == 1) return number;
            var f1 = new FiboTask(number - 1).fork();
            var f2 = new FiboTask(number - 2).fork();
            return f1.join() + f2.join();
        }
    }

    static class PrintTask extends RecursiveAction {

        private final int[] list;
        private final int start;
        private final int end;

        public PrintTask(int[] list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if(start > end) return;
            int mid = (start + end) / 2;
            System.out.println("Num " + list[mid]);
            PrintTask leftTask = new PrintTask(list, start, mid - 1);
            PrintTask rightTask = new PrintTask(list, mid + 1, end);
            invokeAll(leftTask, rightTask);
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(2);
        long input = 20;
        FiboTask task = new FiboTask(input);
        Long result = pool.invoke(task);
        System.out.println("Results " + result);

        PrintTask printTask = new PrintTask(new int[]{1, 2, 3, 4, 5}, 0, 4);
        pool.invoke(printTask);
    }
}
