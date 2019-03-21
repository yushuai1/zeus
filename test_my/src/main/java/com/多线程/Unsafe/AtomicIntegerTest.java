package com.多线程.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] de){
        AtomicInteger atomicInteger = new AtomicInteger(0);
//        atomicInteger.getAndIncrement();

        System.out.println(atomicInteger.incrementAndGet());

    }
}
