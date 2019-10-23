package cn.gltzj.gc;

/**
 * 测试空间分配担保：
 *      发生Minor GC时，虚拟机呼呼检测之前每次晋升到老肩带的平均大小是否大于老年代的剩余大小，如果大于，则改为直接进行一次Full GC，
 *      如果小于，则查看HandlePromotionFailure设置是否允许担保失败：如果允许，则只会进行Minor GC，如果不允许，则要改为一次Full GC。
 *
 *  - 新生代使用的是复制收集算法，为了内存利用率只使用其中一个Survivor空间作为轮换备份，因此当出现大量对象在Minor GC后仍然存活的情况时，
 *  就需要老年代进行担保，让无法进入Survivor的对象直接进入到老年代中。
 *  - 老年代进行担保就需要老年代本身还有能够容纳这些对象的剩余空间，但是通过担保进入老年代的对象大小是无法确定的，
 *  所以只好取之前每次进入到老年代的对象容量的平均大小来作为经验值，与老年代剩余空间进行比较，来决定是否需要进行Full GC来让老年代腾出更多空间。
 *  - 取平均值比较其实是一种动态概率手段，如果某次Minor GC之后存活的对象突增大于平均值的话，还会导致担保失败，如果出现了担保失败，就只能在之后重新进行一次Full GC。
 *  - 大部分情况下还是需要将HandlePromotionFailure打开，来避免Full GC过于频繁。
 *
 *
 *
 * JVM args:
 *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
 *  -XX:-HandlePromotionFailure 关闭空间分配担保
 *  -XX:+HandlePromotionFailure 打开空间分配担保
 *
 */
public class TestAllocationHandlePromotion {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {

        byte[] byte1, byte2, byte3, byte4, byte5, byte6, byte7;

        byte1 = new byte[_1M * 2];
        byte2 = new byte[_1M * 2];
        byte3 = new byte[_1M * 2];
        byte1 = null;
        byte4 = new byte[_1M * 2];
        byte5 = new byte[_1M * 2];
        byte6 = new byte[_1M * 2];
        byte4 = null;
        byte5 = null;
        byte6 = null;
        byte7 = new byte[_1M * 2];

        /**
         *
         * 查看执行结果：
         *
         * 执行完结果总是会提示一行警告：
         *  Warning: The flag -HandlePromotionFailure has been EOL'd as of 6.0_24 and will be ignored
         *
         *  TODO 好像是这个参数没生效，造成这个结果也看不出来有什么明显区别，因为都没有进行Full GC，
         *  正常情况感觉应该进行一次Full GC才对，后续再看看是什么情况
         *
         */

    }
}
