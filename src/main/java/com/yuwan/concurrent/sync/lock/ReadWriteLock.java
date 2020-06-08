package com.yuwan.concurrent.sync.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private Lock read = rw.readLock();
    private Lock write = rw.writeLock();

    public static void main(String[] args) {

    }
    private  class Worker extends Thread{
        public void run(){
            read.lock();
            read.unlock();
            write.lock();
            System.out.println("write....");
            read.lock();
            write.unlock();
            System.out.println("read");
            read.unlock();
        }
    }
}
