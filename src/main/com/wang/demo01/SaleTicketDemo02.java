package main.com.wang.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王艺博
 * @date 2021/8/4 17:33
 */
// 基本的买票
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket02 ticket = new Ticket02();
        new Thread(()->{ for (int i = 0; i < 40; i++) ticket.sale();},"A").start();
        new Thread(()->{ for (int i = 0; i < 40; i++) ticket.sale();},"B").start();
        new Thread(()->{ for (int i = 0; i < 40; i++) ticket.sale();},"C").start();

    }
}

/*
 lock三部曲
    1. new ReentrantLock();
    2. lock()   加锁
    3. unlock()   解锁
 */
class Ticket02 {
    // 属性
    private int number = 30;
    // 买票方法
    Lock lock = new ReentrantLock();

    public  void sale() {
        lock.lock();// 加锁

        try {
            // 业务代码
            if (number>0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票,剩余：" + number
                );
        }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();// 解锁
        }
    }
}

