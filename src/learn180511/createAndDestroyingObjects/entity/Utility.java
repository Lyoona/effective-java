package learn180511.createAndDestroyingObjects.entity;

/**
 * Created by liyoumin on 2018/5/11.
 */
public class Utility {

    //使用默认构造器来确保不可实例化
    private Utility(){
        throw new AssertionError();
    }

    //省略

    public static int add(int x , int y){
        return x+y;
    }
}
