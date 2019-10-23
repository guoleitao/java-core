package cn.gltzj.gc;

/**
 * 测试动态对象年龄判定：
 *  Survivor中相同年龄的所有对象大小的总和大于Survivor空间的一半，或者年龄大于或等于该年龄的对象就可以直接进入老年代，
 *  而无需等到MaxTenuringThreshold的设定值（使用Serial与Serial Old的组合的内存分配与回收策略）
 *
 * JVM args:
 *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 *  -XX:MaxTenuringThreshold 控制对象经过多少次GC之后移动至年老代。
 *      特殊情况：如果在Survivor中相同年龄的所有对象大小的总和大于Survivor空间的一半，
 *              或者年龄大于或等于该年龄的对象就可以直接进入老年代，而无需等到MaxTenuringThreshold的设定值
 *  -XX:+PrintTenuringDistribution 打印对象年龄信息
 */
public class TestAllocationMaxTenuringThresholdSum {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {

        byte[] byte1, byte2, byte3, byte4, byte5;

        byte1 = new byte[_1M / 4];
        byte2 = new byte[_1M / 4];
        byte3 = new byte[_1M * 4];
        byte4 = new byte[_1M * 4];
        byte5 = new byte[_1M * 4];

        /**
         *
         * 查看执行结果：
         *  1.byte1，byte2,byte3对象创建在Eden区，创建byte4对象时发现Eden区存储不够，进行了一次GC
         *  2.GC后发现byte1,byte2可以放入Survivor区，byte3太大放不进Survivor区，所以将byte1,byte2放入Survivor区并且将年龄设为1，byte3移至年老代，并将byte4放入Eden区
         *  3.将byte3置为null，但是没有触发GC，即此时Eden区还是被之前的byte3占用了4M
         *  4.创建byte5时，发现Eden区存储不够，所以又进行一次GC，发现byte1和byte2对象大小的总和大于Survivor空间的一半，
         *      所以直接将byte1和byte2移至年老代，byte4太大放不进Survivor区，也移至年老代，并将byte5放入Eden区
         *  所以最终新生代占用4M（其中Eden区占用4M，即byte5对象），年老代占用（1/4M+1/4M+4M+4M，即byte1，byte2，byte3，byte4）
         */

    }
}
