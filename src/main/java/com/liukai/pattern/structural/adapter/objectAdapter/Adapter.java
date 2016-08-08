package com.liukai.pattern.structural.adapter.objectAdapter;

/**
 * 对象的适配器模式
 * 适配器角色
 * Created by Administrator on 2016/8/8 0008.
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * 源类有方法sampleOperation1，因此适配器类直接委派即可
     */
    @Override
    public void sampleOperation1() {
        adaptee.sampleOperation1();
    }

    /**
     * 由于源类没有方法sampleOperation2，因此适配器补充上这个方法
     */
    @Override
    public void sampleOperation2() {
        //write your code here
    }
}

/**
 * 源角色
 * Created by Administrator on 2016/8/8 0008.
 */
class Adaptee {

    /**
     * 源类含有的方法
     */
    public void sampleOperation1() {
    }
}

/**
 * 目标角色
 * Created by Administrator on 2016/8/8 0008.
 */
interface Target {


    /**
     * 这是源类也有的方法
     */
    void sampleOperation1();

    /**
     * 这是源类没有的方法
     */
    void sampleOperation2();
}