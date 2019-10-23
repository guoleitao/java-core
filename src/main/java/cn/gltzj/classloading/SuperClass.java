package cn.gltzj.classloading;

public class SuperClass {

    static {
        System.out.println("SuperClass  init !");
    }

    public static int val = 3;

    public static final String str = "hello world2";

}
