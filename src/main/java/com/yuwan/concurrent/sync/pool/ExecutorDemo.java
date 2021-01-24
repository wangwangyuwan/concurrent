package com.yuwan.concurrent.sync.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    /**
     * 需要限制线程数量，适用于负载较重服务器
     */
    private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
    /**
     * 单个线程活动，适用于需要顺序执行任务的场景
     */
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    /**
     * 大小无界，适用于 短期异步任务
     */
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    /**
     * 周期任务
     */
    private final ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
    private final ExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
    }
}
