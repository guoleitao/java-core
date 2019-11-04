package thread;

public class AlternateTest1 {

    volatile static int count = 0;

    public static void main(String[] args) {

        /**
         * 线程交替执行-volatile实现方式
         */

        new MyThread1().start();
        new MyThread2().start();

    }

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            this.setName("thread1");
            doWork();
        }

        private void doWork() {

            exec(0,this.getName());
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            this.setName("thread2");
            doWork();
        }

        private void doWork() {
            exec(1,this.getName());
        }
    }


    public static void exec(int i,String name){
        while (true) {
            if (count % 2 == i) {
                System.out.println(name + "   dowork  " + count);
                count++;
            }
            if (count > 10) {
                return;
            }
            System.out.println(System.currentTimeMillis());
        }
    }
}


