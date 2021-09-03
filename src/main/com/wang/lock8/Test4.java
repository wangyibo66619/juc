package main.com.wang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 7. 1个静态同步方法，1个普通同步方法，1个对象， 先打印发短信还是打电话
 * 8. 1个静态同步方法，1个普通同步方法，2个对象， 先打印发短信还是打电话
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象的Class类模板只有一个，static，锁的是Class
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        new Thread(()->{
            phone1.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

// Phone3唯一的一个Class对象
class Phone4{
    // 静态同步方法  锁的Class类模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    // 普通同步方法   锁的调用者
    public synchronized void call() {
        System.out.println("打电话");
    }

}
