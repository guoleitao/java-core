package cn.gltzj.compile;

public class testAssert {

    public static void main(String args[]) {
        int a = 1;
        int b = 1;
        assert a == b;
        System.out.println("true");
        assert a != b : "aaa";
        System.out.println("false");
    }
}
