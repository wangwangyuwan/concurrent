package com.yuwan.concurrent.sync;

/**
 * volatile  关键字：仅保证可见性
 * 当主内存中 值 被修改，通知各线程刷新缓冲区
 */
public class VolatileDemo {
    private volatile boolean running = true;

    private void fast() {
        System.out.println("start");
        while (running) {
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(volatileDemo::fast, "t1").start();
        Thread.sleep(100);

        volatileDemo.running = false;
    }
}
