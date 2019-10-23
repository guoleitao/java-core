package cn.gltzj.bytecodeExeEngine;

/**
 * VM args:
 * -verbose:gc
 */
public class SlotTest {
    public static void main(String[] args) {

//        byte[] pg = new byte[64 * 1024 * 1024];
//        System.gc();



//        {
//            byte[] pg = new byte[64 * 1024 * 1024];
//        }
//        System.gc();


        {
            byte[] pg = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
