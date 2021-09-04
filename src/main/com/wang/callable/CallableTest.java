package main.com.wang.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();   FutureTask是Runnable的实现类
        // new Thread(new FutureTask(Callable<V> callable)).start();
        new Thread().start();// 怎么启动Callable
        MyThread myThread = new MyThread();
        // 适配类
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();// 结果会被缓存，效率高

        Integer o = (Integer) futureTask.get();  // 获取Callable的返回结果 ，get方法可能会产生阻塞
        System.out.println(o);

    }
}

class MyThread implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        // 耗时的操作
        return 1024;
    }
}
