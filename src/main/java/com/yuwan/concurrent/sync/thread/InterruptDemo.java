package com.yuwan.concurrent.sync.thread;

import com.yuwan.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Slf4j
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter_i = new Counter();
        Thread interrupt_t = new Thread(counter_i, "counter-interrupt");
        interrupt_t.start();
        SleepUtils.second(2);
        interrupt_t.interrupt();

        Counter counter_c = new Counter();
        Thread cancel_t = new Thread(counter_c, "counter-cancel");
        cancel_t.start();

        SleepUtils.second(2);
        counter_c.cancel();

    }

    static class Counter implements Runnable {

        private long count;
        private volatile Boolean runable = true;

        @Override
        public void run() {
            while (runable && !Thread.currentThread().isInterrupted()) {
                count++;
            }
            log.info("{}:count={}", Thread.currentThread().getName(), count);
        }

        public void cancel() {
            this.runable = false;
        }
    }

    /**
     * suspend()不会释放锁
     * stop()不能保证线程的资源正常释放
     *
     * @throws InterruptedException
     */
    private static void stop() throws InterruptedException {
        Thread thread = new Thread(new Runner(), "runner");
        thread.setDaemon(true);
        thread.start();

        TimeUnit.SECONDS.sleep(3);

        thread.suspend();
        log.warn("暂停 time : {}", LocalTime.now());

        TimeUnit.SECONDS.sleep(3);

        thread.resume();
        log.warn("恢复 time : {}", LocalTime.now());

        TimeUnit.SECONDS.sleep(3);

        thread.stop();
        log.warn("停止 time : {}", LocalTime.now());

        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 中断
     */
    private static void interruped() {
        Thread busy = new Thread(new Busy(), "busy");
        Thread sleep = new Thread(new Sleep(), "sleep");
        busy.setDaemon(true);
        sleep.setDaemon(true);
        busy.start();
        sleep.start();

        SleepUtils.second(5);

        busy.interrupt();
        sleep.interrupt();
        log.info("busy={}", busy.isInterrupted());
        log.info("sleep={}", sleep.isInterrupted());
    }

    static class Runner implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                log.info("thread={},time={}", Thread.currentThread().getName(), LocalTime.now());
                SleepUtils.second(1);
            }
        }
    }

    static class Busy implements Runnable {

        @Override
        public void run() {
            for (; ; ) {

            }
        }
    }

    static class Sleep implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                SleepUtils.second(10);
            }
        }
    }
}
