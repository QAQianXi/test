package com.example.bigevent;

import org.junit.jupiter.api.Test;


public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet(){
        //提供local对象
        ThreadLocal tl = new ThreadLocal();

        //开启两个线程
        new Thread(() ->{
            tl.set("aa");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"a1").start();
        new Thread(() ->{
            tl.set("bb");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"b1").start();
    }
}
