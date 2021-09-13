package main.com.wang.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

// volatile  不保证原子性
public class VDemo02 {
    // 原子类的
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        // num++; // 不是一个原子操作
        num.getAndIncrement(); // 加1方法
    }
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) { // 20条线程
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {// 每个线程执行一千次
                    add();
                }
            }).start();
        }


        while (Thread.activeCount()>2) { // main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
