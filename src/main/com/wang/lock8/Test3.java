package main.com.wang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5.增加两个静态同步方法,只有一个对象， 打电话还是发短信  发短信
 * 6.两个对象！增加两个静态同步方法, 打电话还是发短信    发短信
 */
public class Test3 {
    public static void main(String[] args) {
        // 两个对象的Class类模板只有一个，static，锁的是Class
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

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
class Phone3{
    // synchronized 锁的对象是方法的调用者!
    // static
    // 类一加载就有了，锁的是Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }

}
