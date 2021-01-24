package com.yuwan.concurrent.sync.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 两线程数据交换
 */
public class ExchangerDemo {
    private static Exchanger e = new Exchanger();

    private static ExecutorService threadService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadService.execute(new Runnable() {
            @Override
            public void run() {
                String a = "a";
                try {
                    Object exchange = e.exchange(a);
                    System.out.println(a + "----->" + exchange);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        threadService.execute(new Runnable() {
            @Override
            public void run() {
                String b = "b";
                try {
                    Object exchange = e.exchange(b);
                    System.out.println("a=" + exchange);
                    System.out.println("b=" + b);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        threadService.shutdown();
    }
}
