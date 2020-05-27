package com.yuwan.concurrent.sync.thread;

public class SyncBlockOrMethod {
    public static void main(String[] args) {
        synchronized (SyncBlockOrMethod.class){

        }

        m();
    }

    private static synchronized void m() {
    }
}
