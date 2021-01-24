package com.yuwan.concurrent;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Todo
 *
 * @author wwyw
 * @since
 */
public class Customer {

    private int offset = 0;
    private String name;
    private List<Integer> list;

    public Customer(List<Integer> list,String name) {
        this.list = list;
        this.name = name;
    }

    public void read() {
        if (CollectionUtils.isEmpty(list) || offset > list.size()) {
            System.out.println("empty!");
            offset = 0;
        }
        Integer integer = list.get(offset);
        System.out.println(String.format("%s:%s", name,integer));
        offset++;
    }
}
