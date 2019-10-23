package cn.gltzj.oom;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * java方法区内存溢出测试
 * 方法区用于存放class类信息，如类名，访问修饰符，常量池，字段描述，方法描述等，
 * 测试溢出需要使用大量类填满方法区，
 * 此处使用cgLib动态创建大量类测试
 *
 * JVM args
 * -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails
 *
 *
 *  -XX:PermSize  非堆（方法区）区域初始内存大小
 *  -XX:MaxPermSize 非堆（方法区）区域分配的最大内存
 */
public class MethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(ObjectOOM.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }

    static class ObjectOOM {

    }
}
