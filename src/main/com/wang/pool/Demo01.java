package main.com.wang.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//   Executors 工具类、三大方法
// 使用线程池之后，使用线程池创建线程
public class Demo01 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程
//        Executors.newFixedThreadPool(5);// 创建一个固定的线程池的大小
//        Executors.newCachedThreadPool();// 可伸缩的，遇强则强，遇弱则弱


        try {
            for (int i = 1; i <= 10; i++) {
                // 使用线程池之后，使用线程池创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }


    }
}
