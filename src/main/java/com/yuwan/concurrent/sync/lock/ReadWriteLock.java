package com.yuwan.concurrent.sync.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private Lock read = rw.readLock();
    private Lock write = rw.writeLock();

    public static void main(String[] args) {

    }
    //锁升级
    private  class Worker extends Thread{
        public void run(){
            read.lock();

            read.unlock();
            write.lock();
            try {
                System.out.println("write....");
                read.lock();
            }finally {

                write.unlock();
            }
            try {
                System.out.println("read");
            }finally {

                read.unlock();
            }
        }
    }
}
