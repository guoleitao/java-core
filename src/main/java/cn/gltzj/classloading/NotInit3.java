package cn.gltzj.classloading;

/**
 * 被动引用3
 */
public class NotInit3 {
    public static void main(String[] args) {
        System.out.println(SuperClass.str);
    }
}
