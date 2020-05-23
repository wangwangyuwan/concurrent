package com.yuwan.concurrent.util;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    public static final void second(int timeout){
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
