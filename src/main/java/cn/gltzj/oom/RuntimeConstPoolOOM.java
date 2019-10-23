package cn.gltzj.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出测试
 * 通过限制方法区大小来限制运行时常量池大小
 * JVM args:
 *  -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 */
public class RuntimeConstPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
