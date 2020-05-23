package com.yuwan.concurrent.sync;

/**
 * synchronizend 关键字：互斥锁（保证原子性和可见性）
 * 锁定对象，非代码块，锁的信息记录在堆内存
 * <p>
 * 1.只对写法加锁，不对读方法加锁，易产生脏读(dirtyRead)
 * 2.可重入锁（一个同步方法调用另外一个同步方法：一个线程已经拥有某个对象的锁，可再次申请该锁）
 * 3.子类可调用父类的sync
 * 4.出异常，锁就会被释放
 *
 * @author wangwangyuwan
 */
public class SyncDemo {

    private static Object o;
    private static int count = 0;

    private static void sync1() {
        synchronized (o) {
            count++;
            System.out.println(count);
        }
    }

    /**
     * sync2 = sync3
     */
    private void sync2() {
        synchronized (this) {
            count++;
            System.out.println(count);
        }

    }

    private synchronized void sync3() {
    }

    /**
     * sync4 = sync5
     */
    private synchronized static void sync4() {
    }

    private static void sync5() {
        synchronized (SyncDemo.class) {

        }
    }

}
