package com.yuwan.concurrent.sync.thread;

import com.yuwan.concurrent.util.SleepUtils;

/**
 *                                  线程的状态
 *                 new->runnable(start():就绪|运行) ->blocked(未获取到锁)
 *      notify()|notifyAll()|LockSupport.unpark()<-->waiting(wait()|join()|LockSupport.park())
 *      notify()|notifyAll()|LockSupport.unpark()<-->timed_waiting(sleep()|wait()|join()|LockSupport.Nanos()|LockSupport.Until())
 *                                                 ->terminated
 */
public class ThreadStateDemo {
    public static void main(String[] args) {

        new Thread(new Blocked(), "blocked-1").start();
        new Thread(new Blocked(), "blocked-2").start();

        new Thread(new Waiting(), "waiting").start();
        new Thread(new TimedWaiting(), "timedWaiting").start();
    }

    /**
     * "blocked-2" #11 prio=5 os_prio=31 tid=0x00007f9df88b1000 nid=0x5103 waiting for monitor entry [0x0000700005393000]
     * java.lang.Thread.State: BLOCKED (on object monitor)
     * <p>
     * "blocked-1" #10 prio=5 os_prio=31 tid=0x00007f9dfa100000 nid=0x4f03 waiting on condition [0x0000700005290000]
     * java.lang.Thread.State: TIMED_WAITING (sleeping)
     */
    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                for (; ; ) {
                    SleepUtils.second(10);
                }
            }
        }
    }

    /**
     * "waiting" #12 prio=5 os_prio=31 tid=0x00007f9df9824800 nid=0x5303 in Object.wait() [0x0000700005496000]
     * java.lang.Thread.State: WAITING (on object monitor)
     */
    static class Waiting implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * "timedWaiting" #13 prio=5 os_prio=31 tid=0x00007f9dfa100800 nid=0x5503 waiting on condition [0x0000700005599000]
     * java.lang.Thread.State: TIMED_WAITING (sleeping)
     */
    static class TimedWaiting implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                SleepUtils.second(10);
            }
        }
    }

}
