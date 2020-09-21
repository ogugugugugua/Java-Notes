package com.sequentialExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
 *
 */
class printWithCountDownLatch extends Thread{
    int num;
    CountDownLatch countDownLatch;
    CountDownLatch nextCountDownLatch;

    printWithCountDownLatch(int num, CountDownLatch countDownLatch, CountDownLatch nextCountDownLatch) {
        this.countDownLatch = countDownLatch;
        this.nextCountDownLatch = nextCountDownLatch;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            if (countDownLatch!=null)
                countDownLatch.await();     //等待前序countDownLatch倒计时，为0则被唤醒继续向下运行
            System.out.println("Thread --> " + num);
            nextCountDownLatch.countDown(); //后序线程倒计时减一
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class method5 {
    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();
        List<CountDownLatch> latchList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            latchList.add(new CountDownLatch(1));       //对于后面的线程，都要等前序线程执行完，所以设置倒计时为1
        }
        for (int i = 0; i < 10; i++) {
            if (i==0)                                   //第一个线程没有前序线程，所以其countDownLatch为null
                threadList.add(new printWithCountDownLatch(i, null, latchList.get(i)));
            else
                threadList.add(new printWithCountDownLatch(i, latchList.get(i - 1), latchList.get(i)));
        }

        System.out.println("方法5：使用CountDownLatch");
        for (int i = 0; i < 10; i++) {
            threadList.get(i).start();
        }
    }
}
