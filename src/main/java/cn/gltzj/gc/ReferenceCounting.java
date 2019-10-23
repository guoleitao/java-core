package cn.gltzj.gc;

/**
 * 测试java是否使用引用计数算法
 *
 *  引用计数算法：给对象中添加一个引用计数器，每当有一个地方引用此对象，计数器就+1，引用失效时，计数器就-1；
 *      任何时刻计数都为0的对象就是不可能再被使用的，以此判断对象已经不再存活
 *
 */
public class ReferenceCounting {

    public Object instance = null;

    private static final int _1M = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1M];

    public static void main(String[] args) {
        ReferenceCounting objA = new ReferenceCounting();
        ReferenceCounting objB = new ReferenceCounting();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();


    }
}
