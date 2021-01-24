package com.yuwan.concurrent.sync.lock;

import com.yuwan.concurrent.util.SleepUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUseCase {
    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        j1 j1 = new j1();
        j1.start();
        System.out.println("interrupt...");
        j1.interrupt();
//        new j2().start();

    }
    static class  j1 extends Thread{
        public void run(){
            coditionWait();
        }
    }
    static class  j2 extends Thread{
        public void run(){
            signal();
        }
    }

    private static void signal() {
        lock.lock();
        System.out.println("signal ...");
        try {
            condition.signal();
            SleepUtils.second(2);
        } finally {
            System.out.println("signal unlock");
            lock.unlock();
            SleepUtils.second(2);
            System.out.println("signal do....");
        }

    }

    private static void coditionWait() {
        lock.lock();
        System.out.println("wait ...");
        try {
            condition.await();
            System.out.println("wait return ...");
        } catch (InterruptedException e) {

        } finally {
            System.out.println("wait unlock");
            lock.unlock();
            SleepUtils.second(2);
            System.out.println("wait do...");
        }
    }
}
