package learn180511.createAndDestroyingObjects.entity;

import java.io.*;

/**
 * Created by liyoumin on 2018/5/11.
 */
public class FileAbout {

    private static int BUFFER_SIZE = 2048;
    // try-finally - No longer the best way to close resources!
    //这代码可能问题不是很大，但是当你再添加一个资源的时候，就会变得糟糕。
    public static String firstLineOfFile(String path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));

        try {
            return br.readLine();
        }finally {
            br.close();
        }
    }


    // try-with-resources - the the best way to close resources!
    public static String firstLineOfFileImprove(String path){
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        }catch (IOException e){
            return "0";
        }
    }

    // try-finally is ugly when used with more than one resource!
    // 如果物理机导致读写失败，甚至导致关闭读写流失败，后一个exception会覆盖前一个错误，导致更难排查错误。所有的问题在java7被解决了，需要引用的资源必须实现AutoCloseable interface
    public static void copy(String src, String dst) throws IOException{
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);

            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0){
                    out.write(buf,0,n);
                }
            }finally {
                out.close();
            }
        }finally {
            in.close();
        }
    }

    public static void copyImprove(String src, String dst) {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
