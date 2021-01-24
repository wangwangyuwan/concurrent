package com.yuwan.concurrent.demo.beans;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * Todo
 *
 * @author wwyw
 * @since
 */
@Slf4j
public class BeansTest {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(info.getPropertyDescriptors()).forEach(beanInfo -> {
            System.out.println(beanInfo);
        });
    }
}
