package com.yuwan.concurrent.sync.aqs;

import com.yuwan.concurrent.util.SleepUtils;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {
    public static void main(String[] args) {

        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                for (; ; ) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}
