package cn.gltzj.gc;

/**
 * finalize()测试点：
 *  1.对象可以在GC时自我拯救
 *  2.这种自救机会只有一次，因为一个对象的finalize()方法最多只会被系统调用一次
 */
public class FinalizeGC {

    public static FinalizeGC inst = null;


    public void isAlive() {
        System.out.println("this is alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize is exe! ");
        inst = this;
    }


    public static void main(String[] args) throws Exception {
        inst = new FinalizeGC();


        inst = null;
        System.gc();
        //finalize优先级低，此处等待0.5秒
        Thread.sleep(500);
        //此处inst不为null，并且finalize方法执行了
        if(inst != null){
            inst.isAlive();
        }else{
            System.out.println(" this is dead");
        }


        inst = null;
        System.gc();
        //finalize优先级低，此处等待0.5秒
        Thread.sleep(500);
        //此处inst为null，并且finalize方法未执行
        if(inst != null){
            inst.isAlive();
        }else{
            System.out.println(" this is dead");
        }



    }
}
