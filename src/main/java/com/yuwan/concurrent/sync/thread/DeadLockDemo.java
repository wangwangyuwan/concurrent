package com.yuwan.concurrent.sync.thread;

/**
 * 线程死锁
 */
public class DeadLockDemo {
    private static Object left = new Object();
    private static Object right = new Object();

    private static void left() {
        synchronized (left) {
            synchronized (right) {

            }
        }
    }

    private static void right() {
        synchronized (right) {
            synchronized (left) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> right()).start();
        new Thread(() -> left()).start();

    }
}
