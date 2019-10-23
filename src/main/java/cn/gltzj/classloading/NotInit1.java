package cn.gltzj.classloading;

/**
 * 被动引用1
 *  对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过子类调用父类的静态字段，只会初始化父类，不会初始化子类。
 * VM args：
 * -XX:+TraceClassLoading
 */
public class NotInit1 {
    public static void main(String[] args) {
        System.out.println(cn.gltzj.classloading.SubClass.val);

        /**
         * 输出结果为 “SuperClass  init !”
         * 但是通过-XX:+TraceClassLoading参数能看到虚拟机触发了子类的加载。
         *
         */
    }

}
