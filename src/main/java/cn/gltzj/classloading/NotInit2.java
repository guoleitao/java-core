package cn.gltzj.classloading;

/**
 * 被动引用2
 *  通过数组定义来引用类，不会触发类的初始化
 * VM args：
 * -XX:+TraceClassLoading
 */
public class NotInit2 {
    public static void main(String[] args) {
        SuperClass[] sus = new SuperClass[10];

        /**
         * 输出结果中没有“SuperClass  init !”，说明父类没有被初始化
         */
    }
}
