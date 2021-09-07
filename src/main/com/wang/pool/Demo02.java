package main.com.wang.pool;


import java.util.concurrent.*;

/*
*   new ThreadPoolExecutor.AbortPolicy()// 银行满了，还有人进来，不处理这个人，抛出异常
*   new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里
    new ThreadPoolExecutor.DiscardPolicy() // 队列满了，丢掉任务，不会抛出异常
    new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试和最早的竞争，不会抛出异常

 */
public class Demo02 {
    public static void main(String[] args) {
        // 自定义线程池

        // 最大线程如何定义
        // 1.CPU 密集型，几核，就是几，可以保持CPU效率最高
        // 2.IO 密集型 > 判断程序中十分耗IO的线程
        System.out.println(Runtime.getRuntime().availableProcessors());// 获取CPU核数
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试和最早的竞争，不会抛出异常
        );

        try {
            // 最大承载 Deque + max
            for (int i = 1; i <= 9; i++) {
                poolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }
    }
}
