package learn180511.createAndDestroyingObjects;

import learn180511.createAndDestroyingObjects.entity.*;

/**
 * Created by liyoumin on 2018/5/11.
 */
public class Learn2 {

    public static void main(String[] args){
        /**
         * 如果构造的参数有很多的情况，那么builder是一个优秀的选择
         * 构造函数和静态工厂方法都不能灵活的扩展大量参数
         * 传统的程序员会如下{@link NutritionFacts}(为了简洁只写了下面几个方法，但是足以说明问题)：
         * 如果你要创建它的实例：需要像下面这样初始化，无论这个参数现在是否想设置，都必须设置。总的来说，如果参数多了，这就不是一个好方法。还有实例化javaBean时，我们不关心它的参数，后面用setter方法注入会更好一点。
         */
        NutritionFacts cocaCola = new NutritionFacts(240,8,100,0,35,27);

        /**
         * 如果使用javaBean模式将会有很大的改善
         * {@link NutritionFacts1}
         */
        NutritionFacts1 cocaCola1 = new NutritionFacts1();
        cocaCola1.setServingSize(240);
        cocaCola1.setServings(8);
        cocaCola1.setCalories(100);
        cocaCola1.setSodium(35);
        cocaCola1.setCarbohydrate(27);

        /**
         * 但是，javaBean模式本身具有严重缺陷，由于构造被拆分为多个set调用，所以javaBean可能导致其不一致的状态，如果使用了不一致的对象可能导致bug,因此需要更费精力来确保线程安全。
         * builder（建造者模式）可以解决这个问题,为了简洁，省略了检查部分，可以在build方法处进行检查，如果参数不对就抛出IllegalArgumentException异常
         */
        NutritionFacts2 cocalCola2 = new NutritionFacts2.Builder()
                .servingSize(240)
                .servings(8)
                .calories(100)
                .fat(0)
                .sodium(35)
                .carbohydrate(27)
                .build();

        /**
         * 这种方法也很适合继承模式，例如:
         * 但是使用这种方式需要当量代码在构造器上，所以只有当参数达到一定规模了之后才会使用这种方式，比如多余4个。
         * 总之这是一个非常优秀的方式
         */
        NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
        Galzone galzone = new Galzone.Builder().addTopping(Pizza.Topping.HAM).sauceInside().build();


        /**
         * 单例模式，如果一个class采用了单例模式，是很难测试的。
         * 实现单例有两种方法
         */
        Elvis elvis = Elvis.getInstance();

        /**
         * 有时一个类用来放置一些方法，但是不可实例化，用下面这种方式就可以解决，但是不可滥用！！！！
         * 通常在Math类或者Array类中会使用
         */
        int result = Utility.add(1,2);

        /**
         * 避免随意创建对象，如果对象可以重用，尽量重用。final的对象总是可以重用的
         * 下面举一个最不应该的例子：
         */
        // DON`T DO THIS! 本身s就是一个对象，new String又是一个对象，如果进行一个for循环，大量无用的String类将被产生!
        // 当然绝大多数（99%?）应该都不会这样写，这里只是举个例子
        String s = new String("bukeyi");
        //请尽量采用下方写法：这种写法保证了只有一个s对象，而不是每次执行都创建一个新的对象，此外可以保证相同虚拟机会复用这个对象。
        //通常可以使用静态工厂方法来避免产生过多的对象，而不是提供两个不可变的构造函数。
        String s1 = "bukeyi";

        /**
         * 有些对象的创建比其它对象昂贵很多，所以需要缓存起来，然而不幸的是，当你创建这样一个对象的时候，并不显而易见。
         * 假如你要判断字符串是否是有效的罗马数字（日常使用通过正则判断url也是常有的情况）
         *
         */
        //下面是最简单的实现：
        boolean roman =RomanNumerals.isRomanNumeral("a");
        //下面是改进的实现,如果频繁的调用，会显著提高性能，而且会提高可读性。
        boolean improveRoman = RomanNumerals.isRomanNumeralImprove("a");

        //请注意！ 在防守重复创建上，往往比创建一个新对象要更危险，不必要的创建只会影响性能，但是防守重复创建上，如果不按要求使用，可能会导致安全漏洞！所以如果你没有足够的掌控能力，还是多创建一个对象吧。

        /**
         * 消除过时的对象引用
         * 如果你从C/C++等需要手动管理内存的语言转向java等具有自动回收机制的语言，你会觉得这像魔法一样，它很容易误导你，让你觉得不必管理内存。
         * 取消过时引用，发送错误时会马上报错，这样的好处是尽早排查错误，这种情况是例外的，不是规范，不要过多的使用这些方法,很没有必要。
         * 一般来说只有当一个类自己管理内存的时候，需要警惕释放内存。
         * 另一种常见的内存泄漏，是cache，一旦你把一个对象的引用放进缓存中，很容易就会忘记，这是有解决办法的：
         * 如果你实现了缓存，只要有一条与外部引用相关，那么就有一个weakHashMap，条目会在过时后自动删除，只有weakHashMap的key被外部引用时，才会存活。
         */
        Stack stack = new Stack();
        stack.push("a");
        stack.pop();

        /**
         * Finalizers在java 9中被弃用，改为cleaner，但是都是不可预测性的，尽量都不使用。
         * C++程序员不要妄想用Finalizer或cleaner来模拟析构函数，也不要依赖它们来关闭文件
         * java中有很多library需要手动来close它们，例如：InputStream,OutputStream and java.sql.Connection 关闭资源常常被忽略，从历史来看，使用try-finally是最好的方法
         */
    }

}
