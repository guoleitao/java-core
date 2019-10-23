package cn.gltzj.oom;
import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出测试
 *
 * JVM args:
 *      -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
 *
 *      Xms 堆的最小值
 *      Xmx 堆的最大值
 *      Xmn 堆中新生代大小
 *      Xms和Xmx设置一致，可以避免堆自动扩展
 *      HeapDumpOnOutOfMemoryError 可以在内存溢出时，dump出当前内存堆转储为快照文件以供事后进行分析
 */
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true){
            list.add(new OOMObject());
        }

    }
}
