package com.yuwan.concurrent.sync.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLanchDemo {

    private static final CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) {


        for (int i = 0; i <2; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    c.countDown();

                }
            }).start();
            System.out.println(i);
        }
        try {

            c.await(1, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(3);

    }
}
