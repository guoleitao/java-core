package cn.gltzj.oom;

/**
 * 虚拟机栈和本地方法栈溢出（HotSpot虚拟机不区分虚拟机栈和本地方法栈）
 * 1.SOF异常（StackOverflowError）
 * 如果线程请求的栈深度大于虚拟机所允许的最大深度，则抛出此异常
 * 2.OOM异常（OutOfMemoryError）
 * 如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出此异常
 * <p>
 * 此处测试Sof异常
 * <p>
 * JVM args:
 * -Xss128k  -XX:+PrintGCDetails
 * -Xss 控制栈容量
 */
public class StackSOF {

    private int stackLen = 1;

    public void stackLeak() {
        stackLen++;
        stackLeak();
    }

    public static void main(String[] args) throws Exception{
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.stackLeak();
        } catch (Exception e) {
            System.out.println("stack 深度为:" + stackSOF.stackLen);
            e.printStackTrace();

            throw e;
        }
    }
}
