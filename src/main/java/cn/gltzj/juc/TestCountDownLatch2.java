package cn.gltzj.juc;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch2 {

    public static void main(String[] args) throws InterruptedException {


        /**
         * 多个线程同时执行,需要在所有线程执行结束之后返回主线程进行操作,可以使用CountDownLatch
         * 多个线程同时执行,某个线程需要在部分线程全部执行之后才开始操作,可以使用CountDownLatch
         *
         * CountDownLatch:
         *      任务分为N个子线程去执行，state也初始化为N（注意N要与线程个数一致）。
         *      这N个子线程是并行执行的，每个子线程执行完后countDown()一次，state会CAS减1。
         *      等到所有子线程都执行完后(即state=0)，会unpark()主调用线程，然后主调用线程就会从await()函数返回，继续后余动作。
         */

        int n = 5;
        CountDownLatch signal = new CountDownLatch(n);

        for (int i = 0; i < n; i++) {
            final int j = i;

            new Thread(() -> {
                Thread.currentThread().setName("Thread " + j);
                System.out.println(Thread.currentThread().getName() + " exec dowork");
                try {
                    Thread.sleep(j * 1000);
                    System.out.println(Thread.currentThread().getName() + "is end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                signal.countDown();
            }).start();
        }


        //等待所有work执行完成
        signal.await();

        //所有work执行完成,主线程在执行
        System.out.println(" all thread is finished!");
    }

}
