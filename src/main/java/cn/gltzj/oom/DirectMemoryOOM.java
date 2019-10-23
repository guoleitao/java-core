package cn.gltzj.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存(DirectMemory)溢出测试
 *  直接内存不是运行时数据区部分，
 *  此处测试可使用 -XX:MaxDirectMemorySize来指定最大直接内存
 * JVM args
 *      -Xmx20M -XX:MaxDirectMemorySize=10M -XX:+PrintGCDetails
 */
public class DirectMemoryOOM {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1M);
        }
    }
}


