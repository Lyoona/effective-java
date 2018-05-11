package learn180511.createAndDestroyingObjects.entity;

import java.util.Objects;

/**
 * Created by liyoumin on 2018/5/11.
 */
public class Elvis {

    private static Elvis INSTANCE;

    private Elvis(){

    }

    public static Elvis getInstance(){
        synchronized (INSTANCE){
            if (INSTANCE == null){
                INSTANCE = new Elvis();
                return INSTANCE;
            }else {
                return INSTANCE;
            }
        }
    }

    //用于反序列化
    private Object readResolve(){
        return INSTANCE;
    }
}
