package com.yuwan.concurrent.sync.aqs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FairAndUnfair {


    public static void main(String[] args) {
        ReentrantLock2 unfair = new ReentrantLock2(false);
        ReentrantLock2 fair = new ReentrantLock2(true);
//        for (int i = 0; i < 5; i++) {
//            Job job = new Job(unfair);
//            job.start();
//        }
        for (int i = 0; i < 5; i++) {
            Job job = new Job(fair);
            job.start();
        }
    }

    static class Job extends Thread {
        private ReentrantLock2 lock;

        Job(ReentrantLock2 lock) {
            this.lock = lock;
        }

        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "->" + lock.getQueueThreads().toString());
            lock.unlock();
        }
    }

    static class ReentrantLock2 extends ReentrantLock {
        ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueueThreads() {
            List<Thread> threads = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(threads);
            return threads;
        }
    }
}
