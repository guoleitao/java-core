package cn.gltzj.gc;

/**
 * 测试大对象直接进入年老代（使用Serial与Serial Old的组合的内存分配与回收策略）
 *
 * JVM args:
 *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
 *  -Xms20M 限制java堆大小为20M
 *  -Xmx20M 限制java堆最大值为 20M
 *  -Xmx和-Xms保持一致限制java堆不可扩展
 *  -Xmn10M 设置java堆中新生代为10M
 *  -XX:SurvivorRatio=8 设置新生代中Eden区和一个Survivor区比例为8:1，即Eden区为8M，两个Survivor各1M
 *  -XX:+UseSerialGC 使用Serial与Serial Old的组合的内存分配与回收策略
 *  -XX:PretenureSizeThreshold 设置大于这个值的对象直接在年老代分配  (此属性只对Serial与ParNew收集器有效)
 */
public class TestAllocationTenured {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {

        byte[] byte1 = new byte[_1M * 4];

        /**
         * 运行结果能看到没有进行GC，年老代最终使用了4M，即byte1对象直接在年老代进行的创建
         */

    }
}
