package main.com.wang.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加 volatile 程序就会死循环
    // 加 volatile 保证可见性
    private volatile static int number = 0;
    public static void main(String[] args) { // main线程

        new Thread(()->{ // 线程一  对主内存的变化不知道
            while (number==0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        number = 1;
        System.out.println(number);
    }
}
