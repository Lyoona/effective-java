package learn180511.createAndDestroyingObjects.entity;

import java.util.regex.Pattern;

/**
 * Created by liyoumin on 2018/5/11.
 */
public class RomanNumerals {

    /**
     * 最简单的实现,但是它最大的问题是太过依赖matches方法，虽然这是最简单的实现方式，但是并不适合在性能苛刻的地方使用
     * 问题在于它在内部创建了一个正则的实例，但是只用了一次就成为了垃圾
     */
    public static boolean isRomanNumeral(String s){
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    /**
     * 为了提供性能，将正则表达式编译为一个实例模式，每次都可以重复调用
     */
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    public static boolean isRomanNumeralImprove(String s){
        return ROMAN.matcher(s).matches();
    }
}
