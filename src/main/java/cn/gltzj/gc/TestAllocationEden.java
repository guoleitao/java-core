package cn.gltzj.gc;

/**
 * 测试对象优先在Eden区分配（使用Serial与Serial Old的组合的内存分配与回收策略）
 *
 * JVM args:
 *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *  -Xms20M 限制java堆大小为20M
 *  -Xmx20M 限制java堆最大值为 20M
 *  -Xmx和-Xms保持一致限制java堆不可扩展
 *  -Xmn10M 设置java堆中新生代为10M
 *  -XX:SurvivorRatio=8 设置新生代中Eden区和一个Survivor区比例为8:1，即Eden区为8M，两个Survivor各1M
 *  -XX:+UseSerialGC 使用Serial与Serial Old的组合的内存分配与回收策略
 */
public class TestAllocationEden {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {

        /**
         * 大多数情况，对象在新生代Eden区中分配，当Eden区没有足够空间时，将发起一次Minor GC
         */

        byte[] byte1, byte2, byte3, byte4;

        byte1 = new byte[_1M * 2];
        byte2 = new byte[_1M * 2];
        byte3 = new byte[_1M * 2];
        byte4 = new byte[_1M * 4];


        /**
         * 通过运行结果能看到：
         *  运行过程中进行了一次GC
         *  运行结束后，Eden区最终使用了4M，年老代使用了6M
         *
         * 执行过程分析：
         *  byte1,byte2,byte3对象直接创建在了Eden区，在创建byte4对象时候，发现Eden区内存不够，进行了一次Minor GC，
         *  然后发现Survivor区也放不下（Survivor区为1M），所以直接将byte1,byte2,byte3对象移动到年老代，然后在Eden区创建byte4对象
         *
         */

    }
}
