package com.yuwan.concurrent.sync.thread;

import com.yuwan.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 当不存在非deamon线程时，jvm会自动退出。
 */
@Slf4j
public class DeamonThreadDemo {
    public static void main(String[] args) {
        Thread t = new Thread(new Job());
        t.setDaemon(true);
        t.start();
    }

    static class Job implements Runnable{

        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally {
                log.info("deamon-thread finally run");
            }
        }
    }
}
