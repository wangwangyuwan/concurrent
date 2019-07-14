package com.yuwan.concurrent.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomicxxx 原子类
 */
public class AtomicClassDemo {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        count.incrementAndGet();//count++
    }
}
