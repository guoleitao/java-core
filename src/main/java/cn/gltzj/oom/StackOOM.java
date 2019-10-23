package cn.gltzj.oom;

/**
 * 虚拟机栈和本地方法栈溢出测试2：
 *  OOM异常
 *  JVM args：
 *      -Xss2M  -XX:+PrintGCDetails
 *
 *  此处会造成windows系统假死，请谨慎操作
 */
public class StackOOM {


    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOOM stackOOM = new StackOOM();
        stackOOM.stackLeakByThread();
    }
}
