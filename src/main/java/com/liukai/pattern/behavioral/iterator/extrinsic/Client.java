package com.liukai.pattern.behavioral.iterator.extrinsic;

/**
 * 外禀迭代器模式
 * Created by Administrator on 2016/12/26 0026.
 */
public class Client {

    public static void main(String[] args) {

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
 */
abstract class Aggregate<T> {

    /**
     * 工厂方法，返回一个迭代器对象
     *
     * @return 迭代器对象
     */
    public abstract Iterator<T> createIterator();

    public abstract int size();

    public abstract T getElement(int index);
}

class ConcreteAggregate<E> extends Aggregate<E> {

    private Object[] objects = {"aaa", "bbb", "ccc"};

    @Override
    public Iterator<E> createIterator() {
        return new ConcreteIterator<>(this);
    }

    /**
     * 取值方法：向外界提供聚集元素
     *
     * @param index
     * @return
     */
    public E getElement(int index) {
        if (index < objects.length) {
            return (E) objects[index];
        } else {
            return null;
        }
    }

    /**
     * 向外界提供给聚集的大小
     *
     * @return
     */
    public int size() {
        return objects.length;
    }
}

/**
 * 抽象迭代器角色
 */
interface Iterator<T> {

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

class ConcreteIterator<E> implements Iterator {

    private Aggregate aggregate;
    private int index;
    private int size;

    public ConcreteIterator(Aggregate<E> aggregate) {
        this.aggregate = aggregate;
        size = aggregate.size();
        index = 0;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if (index < size) {
            index++;
        }
    }

    @Override
    public boolean isDone() {
        return (index >= size);
    }

    @Override
    public Object currentItem() {
        return aggregate.getElement(index);
    }
}
















