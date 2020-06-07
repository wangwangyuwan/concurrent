package com.yuwan.concurrent.sync.thread.pool;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    static DefaultThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>();
    String basepath;
    static ServerSocket serverSocket;
    static int port = 8080;

    static class HttpRequestHandler implements Runnable {
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {


        }
    }
}
