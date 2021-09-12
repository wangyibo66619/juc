package main.com.wang.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/*
*   怎么使用forkjoin
*   1.forkjoinpool通过它来执行
*   2.计算任务frokjoinPool.execute(ForkJoinTask task)
*   3.计算类要继承ForkJoinTask
*/
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    // 临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    // 计算方法
    @Override
    protected Long compute() {
        if ((end-start)<temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {// forkJoin
            long middle = (end+start)/2;// 中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start,middle);
            task1.fork();// 拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1,end);
            task2.fork();// 拆分任务，把任务压入线程队列

            return task1.join()+task2.join();
        }
    }
}
