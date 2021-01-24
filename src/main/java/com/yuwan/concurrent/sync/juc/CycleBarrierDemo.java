package com.yuwan.concurrent.sync.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 循环屏障
 */
public class CycleBarrierDemo {
    private static final CyclicBarrier c = new CyclicBarrier(2);
    private static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }

        c.reset();


//        System.out.println(c.getParties());

    }
}
