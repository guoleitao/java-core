package thread;

public class AlternateTest2 {

    static int count = 0;

    public static void main(String[] args) {

        /**
         * 线程交替执行
         *  1.synchronize方式实现
         *  2.ReentrantLock实现方式
         */

        new AlternateTest2().main1();

    }

    public void main1(){
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

            exec(0,this.getName());
        }
    }

     class MyThread2 extends Thread {
        @Override
        public void run() {
            this.setName("thread2");
            doWork();
        }

        private void doWork() {
            exec(1,this.getName());
        }
    }


    /**
     * synchronize实现
     * @param i
     * @param name
     */
//    public void exec(int i,String name){
//        while (true) {
//            synchronized (this){
//                if (count % 2 == i) {
//                    System.out.println(name + "   dowork  " + count);
//                    count++;
//                }
//                if (count > 10) {
//                    return;
//                }
//            }
//            System.out.println(System.currentTimeMillis());
//        }
//    }

    /**
     * 重入锁实现
     * @param i
     * @param name
     */
//    public void exec(int i,String name){
//        ReentrantLock lock = new ReentrantLock();
//        while (true) {
//            lock.lock();
//            try {
//                if (count % 2 == i) {
//                    System.out.println(name + "   dowork  " + count);
//                    count++;
//                }
//                if (count > 10) {
//                    return;
//                }
//                System.out.println(System.currentTimeMillis());
//            } finally {
//                lock.unlock();
//            }
//
//        }
//    }

    public void exec(int i,String name){
        while (true) {
            try {
                if(count % 2==0){
                    MyThread1.class.wait();
                }else{
                    MyThread2.class.wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (count % 2 == i) {
                    System.out.println(name + "   dowork  " + count);
                    count++;
                }
                if (count > 10) {
                    return;
                }
                System.out.println(System.currentTimeMillis());
            } finally {
                if(count % 2==0){
                    MyThread2.class.notify();
                }else{
                    MyThread1.class.notify();
                }
            }

        }
    }
}





