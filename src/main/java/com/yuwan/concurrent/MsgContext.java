package com.yuwan.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * Todo
 *
 * @author wwyw
 * @since
 */

public class MsgContext {

    private List<Integer> list  = new ArrayList<>();

    public void push(int s){
        list.add(s);
    }

    private List<Integer> get(){
        return list;
    }

    public static void main(String[] args) {
        MsgContext msgContext = new MsgContext();
        msgContext.push(1);
        msgContext.push(2);
        msgContext.push(3);
        List<Integer> msgQueue = msgContext.get();
        Customer customerA = new Customer(msgQueue,"A");
        Customer customerB = new Customer(msgQueue,"B");
        customerA.read();
        customerA.read();
        customerB.read();
    }
}
