package cn.gltzj.gc;

/**
 * 测试长期存活的对象将进入年老代（使用Serial与Serial Old的组合的内存分配与回收策略）
 *
 * JVM args:
 *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 *  -Xms20M 限制java堆大小为20M
 *  -Xmx20M 限制java堆最大值为 20M
 *  -Xmx和-Xms保持一致限制java堆不可扩展
 *  -Xmn10M 设置java堆中新生代为10M
 *  -XX:SurvivorRatio=8 设置新生代中Eden区和一个Survivor区比例为8:1，即Eden区为8M，两个Survivor各1M
 *  -XX:+UseSerialGC 使用Serial与Serial Old的组合的内存分配与回收策略
 *  -XX:MaxTenuringThreshold 控制对象经过多少次GC之后移动至年老代。
 *      特殊情况：如果在Survivor中相同年龄的所有对象大小的总和大于Survivor空间的一半，
 *              或者年龄大于或等于该年龄的对象就可以直接进入老年代，而无需等到MaxTenuringThreshold的设定值
 *  -XX:+PrintTenuringDistribution 打印对象年龄信息
 */
public class TestAllocationMaxTenuringThreshold {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {

        /**
         * 虚拟机分代回收思想：
         *  1.能够区分哪些对象能够放在新生代，哪些对象能够放在年老代
         *  2.为了区分对象应该放在新生代或者年老代，虚拟机给每个对象定义了对象年龄计数器
         *  3.如果对象在Eden区创建，经过第一次Minor GC能够存活，并且能被Survivor区容纳，则会被移动到Survivor区，并将对象年龄设为1
         *  4.对象年龄增加到一定程度（默认15）时，将会移动至年老代中，控制阈值的参数为-XX:MaxTenuringThreshold
         */

        /**
         * 以下测试代码可以通过控制阈值为1和阈值为15分别测试查看执行结果
         */
        byte[] byte1, byte2, byte3;

        byte1 = new byte[_1M / 4];
        byte2 = new byte[_1M * 4];
        byte3 = new byte[_1M * 4];
        byte3 = null;
        byte3 = new byte[_1M * 4];

        /**
         * -XX:MaxTenuringThreshold=1时，查看执行结果：
         *  1.byte1，byte2对象创建在Eden区，创建byte3对象时发现Eden区存储不够，进行了一次GC
         *  2.GC后发现byte1可以放入Survivor区，byte2太大放不进Survivor区，所以将byte1放入Survivor区并且将年龄设为1，byte2移至年老代，并将byte3放入Eden区
         *  3.将byte3置为null，但是没有触发GC，即此时Eden区还是被之前的byte3占用了4M
         *  4.对byte3重新赋值时，发现Eden区存储不够，所以又进行一次GC，发现之前的byte3已经置为null，故直接清理占用的内存区域，并将新的byte3放在Eden区
         *  所以最终Eden区占用4M，年老代占用4M+4M/4
         *
         *
         * -XX:MaxTenuringThreshold=15时，查看执行结果：
         *  1.byte1，byte2对象创建在Eden区，创建byte3对象时发现Eden区存储不够，进行了一次GC
         *  2.GC后发现byte1可以放入Survivor区，byte2太大放不进Survivor区，所以将byte1放入Survivor区并且将年龄设为1，byte2移至年老代，并将byte3放入Eden区
         *  3.将byte3置为null，但是没有触发GC，即此时Eden区还是被之前的byte3占用了4M
         *  4.对byte3重新赋值时，发现Eden区存储不够，所以又进行一次GC，
         *  5.GC后byte1在Survivor中年龄+1，变为2,发现之前的byte3已经置为null，故直接清理占用的内存区域，并将新的byte3放在Eden区
         *  所以最终新生代占用4M+4M/4（其中Eden区占用4M，Survivor占用4M/4），年老代占用4M
         */

    }
}
