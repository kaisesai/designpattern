package com.liukai.pattern.behavioral.iterator.intrinsic;

/**
 * 内禀迭代器
 * Created by Administrator on 2016/12/27 0027.
 */
public class Client {

    public static void main(String[] args){

        Aggregate<String> aggregate = new ConcreteAggregate<>();
        Iterator<String> iterator = aggregate.createIterator();
        while(!iterator.isDone()){
            String currentItem = iterator.currentItem();
            System.out.println(currentItem);
            iterator.next();
        }
    }
}

/**
 * 抽象聚集角色
 * @param <T>
 */
abstract class Aggregate<T>{

    /**
     * 工厂方法，返回一个迭代器对象
     *
     * @return 迭代器对象
     */
    public abstract Iterator<T> createIterator();

    public abstract int size();
}

/**
 * 抽象迭代器角色
 * @param <T>
 */
interface Iterator<T>{

    /**
     * 迭代方法：移动到第一个元素
     */
    void first();

    /**
     * 迭代方法：移动到下一个元素
     */
    void next();

    /**
     * 迭代方法：是否为最后一个元素
     *
     * @return
     */
    boolean isDone();

    /**
     * 迭代方法：返回当前元素
     *
     * @return
     */
    T currentItem();
}

/**
 * 具体
 * @param <T>
 */
class ConcreteAggregate<T> extends Aggregate<T>{

    private Object[] objects={"aa","bb","cc"};

    @Override
    public Iterator<T> createIterator() {
        return new ConcreteIterator();
    }

    @Override
    public int size() {
        return objects.length;
    }

    /**
     * 内部类_具体迭代器（黑箱实现）
     * 描述：因为属于具体聚集类的成员，可以随时访问聚集元素，所以聚集类不需要对外提供获取聚集元素的方法。
     */
    private class ConcreteIterator implements Iterator<T>{

        private int currentIndex=0;

        @Override
        public void first() {
            currentIndex=0;
        }

        @Override
        public void next() {
            if(currentIndex<ConcreteAggregate.this.objects.length){
                currentIndex++;
            }
        }

        @Override
        public boolean isDone() {
            return currentIndex>=objects.length;
        }

        @Override
        public T currentItem() {
            return (T) objects[currentIndex];
        }
    }
}