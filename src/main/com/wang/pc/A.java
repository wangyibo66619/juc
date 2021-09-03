package main.com.wang.pc;

/**
 * 线程之间的通信问题：生产者和消费者问题！  等待唤醒，通置唤醒
 * 线程交替运行 A  B 操作同一个变量   num=0
 * A num+1
 * B num-1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();

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
class Data{// 数字  资源类

    private int number = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        while (number!=0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程我+1完毕了
        this.notifyAll();
    }

    // +1
    public synchronized void decrement() throws InterruptedException {
        while (number==0) {
            // 等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 通知其他线程我-1完毕了
        this.notifyAll();
    }
}
