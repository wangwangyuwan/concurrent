package com.yuwan.concurrent.sync.thread.pool;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKERS_NUMBERS = 10;
    private static final int DEFAULT_WORKERS_NUMBERS = 5;
    private static final int MIN_WORKERS_NUMBERS = 1;
    //工作列表
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    private int workerNum = DEFAULT_WORKERS_NUMBERS;

    private AtomicInteger threadNum = new AtomicInteger();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKERS_NUMBERS);
    }

    public DefaultThreadPool(int workerNum) {
        workerNum = workerNum > MAX_WORKERS_NUMBERS ? MAX_WORKERS_NUMBERS : workerNum < MIN_WORKERS_NUMBERS ? MIN_WORKERS_NUMBERS : workerNum;
        initializeWorkers(workerNum);
    }

    private void initializeWorkers(int defaultWorkersNumbers) {
        for (int i = 0; i < defaultWorkersNumbers; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread t = new Thread(worker, "t-worker-" + threadNum.incrementAndGet());
            t.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (null != job) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        if (0 < num) {
            synchronized (jobs) {
                num = this.workerNum + num > MAX_WORKERS_NUMBERS ? MAX_WORKERS_NUMBERS - this.workerNum : num;
                initializeWorkers(num);
                this.workerNum += num;
            }
        }
    }

    @Override
    public void removeWorkers(int num) {
        if (0 < num) {
            synchronized (jobs) {
                if (num > this.workerNum)
                    throw new IllegalArgumentException("beyond worknum");
                int count = 0;
                while (count < num) {
                    Worker worker = workers.get(count);
                    workers.remove(worker);
                    worker.shutdown();
                    count++;
                }
                this.workerNum -= count;
            }
        }

    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    if (jobs.isEmpty()) {
                        try {
                            log.info("{} -> wait.", Thread.currentThread().getName());
                            jobs.wait();
                        } catch (InterruptedException e) {
                            log.info("{} -> interruptde.", Thread.currentThread().getName());
                            Thread.currentThread().interrupt();
                            return;
                        }
                    } else {
                        job = jobs.removeFirst();
                    }
                }
                if (null != job)
                    job.run();
            }
        }

        public void shutdown() {
            running = false;
        }
    }


}
