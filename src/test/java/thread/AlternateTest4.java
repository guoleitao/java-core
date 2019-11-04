package thread;

public class AlternateTest4 {

    public static void main(String[] args) throws InterruptedException {

        // 计数器
        Holder<Integer> counter = new Holder<>(0);

        // 线程数
        int threads = 5;

        // 锁
        Object lock = new Object();

        for (int i = 0; i < threads; i++) {
            final int index = i;
            new Thread(() -> {
                while (true) {

                    // 等待条件
                    synchronized (lock) {
                        while ((counter.getValue() % threads) != index) {
                            try {
                                lock.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    // 获取计数
                    int count = counter.getValue();

                    // 检查是否交替执行
                    if (count % threads != index) {
                        throw new IllegalStateException(index + " -> " + count);
                    }

                    // 打印
                    System.out.println(String.format("thread %d print %d", index, count));

                    // 唤醒下一个线程
                    synchronized (lock) {
                        counter.setValue(count + 1);
                        lock.notifyAll();
                    }
                }
            }).start();
        }
    }

    static class Holder<T> {
        private T value;

        public Holder(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
