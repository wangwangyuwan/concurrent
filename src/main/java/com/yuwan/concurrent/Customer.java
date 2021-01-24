package com.yuwan.concurrent;

/**
 * Todo
 *
 * @author wwyw
 * @since
 */
public class CustomerA  implements MsgDemo {

    @Override
    public void send(String msg) {
        System.out.println(String.format("a:%s",msg));
    }
}
