package thread;

public class AlternateTest3 {

    static int count = 0;

    public static void main(String[] args) {

        /**
         * 线程交替执行
         *  wait/notify实现
         */

        new AlternateTest3().main1();

        try {
            Thread.sleep(1000);
            synchronized (AlternateTest3.class) {
                AlternateTest3.class.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void main1() {
        new MyThread1().start();
        new MyThread2().start();
    }

    class MyThread1 extends Thread {
        @Override
        public void run() {
            this.setName("thread1");
            doWork();
        }

        private void doWork() {

            exec(0, this.getName());
        }
    }

    class MyThread2 extends Thread {
        @Override
        public void run() {
            this.setName("thread2");
            doWork();
        }

        private void doWork() {
            exec(1, this.getName());
        }
    }

    public void exec(int i, String name) {
        while (true) synchronized (AlternateTest3.class) {

            try {

                try {
                    AlternateTest3.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count > 10) {
                    return;
                }
                System.out.println(name + "   dowork  " + count);
                count++;


                System.out.println(System.currentTimeMillis());
            } finally {
                try {
                    AlternateTest3.class.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}





