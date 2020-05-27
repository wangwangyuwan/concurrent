package com.yuwan.concurrent.sync.thread;

import com.yuwan.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

/**
 * 等待／通知模式
 *
 * 1- 获取对象的锁
 * 2- 改变条件
 * 3- 通知等待的线程
 */
@Slf4j
public class WaitAndNotify {
    private static boolean flag = true;

    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(new Wait(), "wait-w");
        thread.start();
        SleepUtils.second(5);
        Thread thread2 = new Thread(new Notify(), "wait-t");
        thread2.start();

    }

    private static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        log.info("{} flag is true @{}",Thread.currentThread(),LocalTime.now());
                        SleepUtils.second(5);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            log.info("{} flag is false @{}",Thread.currentThread(),LocalTime.now());

        }
    }

    private static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock){
                lock.notifyAll();
                flag = false;
                log.info("{} hold lock notify @{}",Thread.currentThread(), LocalTime.now());
                SleepUtils.second(5);
            }
            synchronized (lock){
                log.info("{} hold lock again sleep notify @{}",Thread.currentThread(), LocalTime.now());
                SleepUtils.second(5);
            }
        }
    }
}
