package learn180511.createAndDestroyingObjects;

/**
 * Created by liyoumin on 2018/5/11.
 */
public class Learn1 {

    public static void main(String[] args){
        Boolean a = valueOf(true);
    }

    //方法1 考虑静态
    //方法2 工厂方法代替构造器

    /**
     * 传统的方法是提供一个公共构造器
     * 例如：
     * public CreateAndDestroyingObjects() {}
     * 还有一种技术应该是每个程序员的技能包
     * 提供一种静态方法
     * 比如：下面是一个将基础的bool型包装为Boolean类的方法
     * 注意！： 这里的静态工厂方法不和设计模式中的静态工厂方法等同
     * 又或者在Android程序员经常会用到的Activity跳转方式
     * public static void ActivityStart(Context context){
     *     context.startActivity(new Intent(context,Activity::this));
     * }
     * 这样的写法，有优点但是也有缺点：
     * 优点是：1.
     *        不同于构造方法，他们可以有自己的名字。
     *        如果构造函数没有一个返回类型，那么有这样一个名字无疑是利于使用和阅读的。
     *        有许多人会采用多态的方式，提供不同参数的构造函数，这是个馊主意，这种API的使用者将永远无法记住使用的构造函数是哪一个的，不阅读文档也根本不会知道使用这个API将会发生什么。
     *        而静态工厂方法能够使用精确的名字来表示不同
     *        2.
     *        不同于构造函数，不需要每次调用都创建新对象，可以预先构造实例，避免重复创建。
     *        上面的Boolean valueOf就说明了这种情况，它不需要创建一个对象，这种方式有点类似享元模式，如果经常请求等效的对象，特别是创建对象的代价很高的时候，它的优势就体现出来了。
     *        上述这样被称为实例控制，实例控制可以保证它是单例的或非实例化的。此外它还保证了如果a.equals(b)一定是在a==b的情况下，这是享元模式的基础。Enum类型就是用了它
     *        3.
     *        与构造函数不同，它们能返回各种类型的任何子类的对象。
     *        此技术适用于基于接口的框架，java许多的collections， synchronized collections方法都是用的这种方法来提供接口的
     *        4.
     *        返回对象的类别可以根据调用参数的变化而变化
     *        可以随时增减相关的api而不会有影响
     *        5.
     *        当该方法的类被写入的时候,返回对象的类不必存在
     *        jdbc便是这样的一个例子：服务提供者框架中有三个基本组件：一个服务接口，它代表一个实现；一个提供者注册API，提供者用来注册实现；以及一个服务访问API，客户端用来获取服务的实例。
     *
     * 缺点是：1.没有public或protected的构造器不能被子类化
     *        2.很难被程序员找到: from ， of都是这种方法常用的类名部分，请注意:
     *          BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
     *          StackWalker luke = StackWalker.getInstance(options);
     *
     * 通常这种方式是优秀的
     */
    public static Boolean valueOf(boolean b){
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

}
