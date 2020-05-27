package com.yuwan.concurrent.sync.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道输入／输出流-线程间数据传输
 */
public class PipedDemo {
    public static void main(String[] args) throws IOException {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();

        out.connect(in);
        new Thread(new Print(in), "p1").start();

        out.write("hello hey hey hey");
        out.flush();
        out.close();
    }

    private static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {

                while ((receive = in.read()) != -1) {

                    System.out.print((char) receive);
                }
            } catch (IOException e) {
            }
        }
    }

}
