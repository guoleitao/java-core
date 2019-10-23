package cn.gltzj.juc;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

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
        CountDownLatch initSignal = new CountDownLatch(1);
        CountDownLatch signal = new CountDownLatch(n);

        for (int i = 0; i < n; i++) {
            new MyWorker(initSignal, signal, i).start();
        }


        //所有线程开始执行之前需要进行初始化操作
        init();
        //初始化结束,通知所有work开始执行
        initSignal.countDown();

        //等待所有work执行完成
        signal.await();

        //所有work执行完成
        System.out.println(" all thread is finished!");

    }

    private static void init() {

        System.out.println("main is init!");
    }
}


class MyWorker extends Thread {

    private CountDownLatch initSignal = null;
    private CountDownLatch signal = null;
    private int i;

    MyWorker(CountDownLatch initSignal, CountDownLatch signal, int i) {
        this.initSignal = initSignal;
        this.signal = signal;
        this.i = i;
    }

    @Override
    public void run() {

        try {
            this.initSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doWrok();
        this.signal.countDown();
    }

    private void doWrok() {
        this.setName("Thread " + this.i);
        System.out.println(this.getName() + " exec dowork");
        try {
            Thread.sleep(this.i * 1000);
            System.out.println("thread is : " + this.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}