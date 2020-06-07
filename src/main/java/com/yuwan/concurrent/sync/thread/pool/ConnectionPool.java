package com.yuwan.concurrent.sync.thread.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initializeSize) throws SQLException {
        while (pool.size()<initializeSize){
            pool.addLast(DriverManager.getConnection("localhost"));
        }
    }

    public void releaseConnection(){

    }
    public void fatchConnection(Connection connection){

    }

}
