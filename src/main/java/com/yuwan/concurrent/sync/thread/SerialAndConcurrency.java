package com.yuwan.concurrent.sync.thread;

/**
 * 累加操作小于（lt;）百万次
 * 单线程串行：更快
 * 多线程并发：创建线程和上下文切换都需要开销
 */
public class SerialAndConcurrency {
    private static final Integer count = 1000001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    public static void concurrency() throws InterruptedException {
        System.out.println("concurrency");
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (Integer i = 0; i < count; i++) {
                    a += 5;
                }
                System.out.println("a="+a);
            }
        });
        thread.start();
        int b = 0;
        for (Integer i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        System.out.print("times:");
        System.out.println(System.currentTimeMillis()-start);
        System.out.println("b="+b);


    }

    public static void serial() {
        System.out.println("serial");
        long start = System.currentTimeMillis();

        int a = 0;
        for (Integer i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (Integer i = 0; i < count; i++) {
            b--;
        }
        System.out.print("times:");
        System.out.println(System.currentTimeMillis()-start);
        System.out.println("b="+b);

    }

}
