package main.com.wang.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

// 懒汉式单例
public class LazyMan {

    private static boolean haha = false;
    private LazyMan() {
        synchronized (LazyMan.class) {
            if(haha==false) {
                haha=true;
            }else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    public volatile static LazyMan lazyMan;
    // 双重检测锁模式的 懒汉式单例  DCL 懒汉式
    public static LazyMan getInstance() {
        if (lazyMan==null) {
            synchronized (LazyMan.class) {
                if (lazyMan==null) {
                    lazyMan = new LazyMan();// 不是一个原子性操作
                    /*
                    *   1.分配内存空间
                    *   2.执行构造方法，初始化对象
                    *   3.把这个对象指向这个空间
                    *
                    *   123
                    *   132 A
                    *       B // 此时lazyMan还没有完成构造
                    *
                    * */
                }
            }
        }
        return lazyMan;
    }
    // 反射
    
    public static void main(String[] args) throws Exception {
        // LazyMan instance1 = LazyMan.getInstance();
        Field haha = LazyMan.class.getDeclaredField("haha");
        haha.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance1 = declaredConstructor.newInstance();

        haha.set(instance1,false);

        LazyMan instance2 = declaredConstructor.newInstance();


        System.out.println(instance1);
        System.out.println(instance2);
    }
    
    
    
    
    
    /*// 多线程并发
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                lazyMan.getInstance();
            }).start();
        }
    }*/
}
