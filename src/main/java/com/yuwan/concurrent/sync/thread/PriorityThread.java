package com.yuwan.concurrent.sync.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试优先级
 */
@Slf4j
public class PriorityThread {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i > 5 ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread t = new Thread(job);
            t.setPriority(priority);
            t.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        jobs.forEach(t -> {
            log.info("priority={},count={}", t.priority, t.count);
        });
    }

    static class Job implements Runnable {
        private int priority;
        private int count;

        Job(int priority) {
            this.priority = priority;
        }


        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                count++;
            }
        }
    }
}

