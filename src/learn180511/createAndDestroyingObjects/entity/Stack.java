package learn180511.createAndDestroyingObjects.entity;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by liyoumin on 2018/5/11.
 * 这个程序没有什么明显的错误，你可以随意测试，它能通过任何测试，但是它有一个隐藏的错误。
 * 简单的说，它有一个内存泄漏，它可以默默的表现为性能下降。极端的情况下会导致OutOfMeMyLogyError
 * 内存泄漏在哪？
 * 如果栈增加再收缩，从栈pop出来的对象不会被回收，即时使用Stack的程序没有更多的引用它，这是因为stack保持了这些过时的引用
 */
public class Stack {

    private Object[] elements;

    private int size = 0;
    private static final int DEFAULT_INSTALL_CAPACITY = 16;

    public Stack(){
        elements = new Object[DEFAULT_INSTALL_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop(){
        if (size == 0){
            throw new EmptyStackException();
        }

        return elements[--size];
//        修复方法：
//        Object result = elements[--size];
//        elements[size] = null;
//
//        return result;

    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     * */
    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements,2*size + 1);
        }
    }
}
