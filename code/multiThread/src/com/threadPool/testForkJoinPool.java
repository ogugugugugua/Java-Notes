package com.threadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class testForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }

    static class AddTaskReturn extends RecursiveTask<Long> {
        int start, end;

        AddTaskReturn(int s, int e) {
            start = s;
            end = e;
        }
        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = start + (end - start) / 2;
                AddTaskReturn subTask1 = new AddTaskReturn(start, middle);
                AddTaskReturn subTask2 = new AddTaskReturn(middle, end);
                subTask1.fork();    //分叉
                subTask2.fork();    //分叉
                return subTask1.compute() + subTask2.compute();
            }
        }
    }
    static class AddTask extends RecursiveAction {
        int start, end;

        AddTask(int s, int e) {
            start = s;
            end = e;
        }
        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from: " + start + " to: " + end + " = " + sum);
            } else {
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();    //分叉
                subTask2.fork();    //分叉
            }
        }
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        AddTaskReturn task = new AddTaskReturn(0, nums.length);
        forkJoinPool.execute(task);
        Long aLong = task.get();
        System.out.println("result: " + aLong);
    }
}
