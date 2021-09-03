package main.com.wang.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通信问题：生产者和消费者问题！  等待唤醒，通置唤醒
 * 线程交替运行 A  B 操作同一个变量   num=0
 * A num+1
 * B num-1
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();

    }
}

// 判断等待，业务，通知
class Data2{// 数字  资源类

    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

// condition.await();// 等待
// condition.signalAll();// 唤醒

    // +1
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 业务代码
            while (number!=0) {
                // 等待
                condition.await();// 等待
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 通知其他线程我+1完毕了
            condition.signalAll();// 唤醒
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // +1
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 业务代码
            while (number==0) {
                // 等待
                condition.await();// 等待
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 通知其他线程我+1完毕了
            condition.signalAll();// 唤醒
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
