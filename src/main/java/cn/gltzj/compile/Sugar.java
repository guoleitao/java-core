package cn.gltzj.compile;

import java.util.Arrays;
import java.util.List;

public class Sugar {

    public static void main(String[] args) {
        Integer a =1;
        Integer b =2;
        Integer c =3;
        Integer d =3;
        Integer e =127;
        Integer f =127;
        Long g =3L;

        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));//��==��������������������Զ����в��䣬û��������������������Զ�����
        System.out.println(g.equals(a+b));//equals()�������ᴦ����������ת���Ĺ�ϵ



        List<Integer> list = Arrays.asList(1,2,3,4);

        int sum = 0;
        for (int i : list) {
            sum+=i;
        }

        String[] arr = new String[]{"a","b","c"};

        for (String str : arr) {
            System.out.println(str);
        }


        if(true){
            System.out.println(111);
        }else{
            System.out.println(222);
        }

//        while(true){
//            System.out.println("");
//        }

        test("sss","ddd","eee");


        int v = 10_00_00;

    }


    public static void test(String... strs)
    {
        for (int i = 0; i < strs.length; i++)
        {
            System.out.println(strs[i]);
        }
    }
}
