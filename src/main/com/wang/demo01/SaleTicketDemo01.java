package main.com.wang.demo01;

import java.util.concurrent.locks.Lock;

/**
 * @author 王艺博
 * @date 2021/8/4 17:33
 */
// 基本的买票
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类，将资源类丢入线程
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"C").start();
    }
}
// 资源类
class Ticket {
    // 属性
    private int number = 30;
    // 买票方法
    // synchronized  本质：队列、锁
    public synchronized void sale() {
            if (number>0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票,剩余：" + number
                );
        }
    }
}

