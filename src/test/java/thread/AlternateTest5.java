package thread;

import java.util.concurrent.CountDownLatch;

public class AlternateTest5 {

    volatile static int count = 0;

    static CountDownLatch ca = new CountDownLatch(1);
    static CountDownLatch cb = new CountDownLatch(1);

    public static void main(String[] args) {

        /**
         * 线程交替执行-CountDownLatch实现方式
         */

        new MyThread1().start();
        new MyThread2().start();


        try {
            Thread.sleep(10000);
            ca.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            this.setName("thread1");
            doWork();
        }

        private void doWork() {

            exec(0, this.getName());
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            this.setName("thread2");
            doWork();
        }

        private void doWork() {
            exec(1, this.getName());
        }
    }


    public static void exec(int i, String name) {
        while (true) {

            try {
                if(i==0){
                    ca.await();
                    ca = new CountDownLatch(1);
                } else if(i==1){
                    cb.await();
                    cb = new CountDownLatch(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count % 2 == i) {
                System.out.println(name + "   dowork  " + count);
                count++;
            }
            if (count > 10) {
                return;
            }
            if(i==0){
                cb.countDown();
            } else if(i==1){
                ca.countDown();
            }
            System.out.println(System.currentTimeMillis());
        }
    }
}


