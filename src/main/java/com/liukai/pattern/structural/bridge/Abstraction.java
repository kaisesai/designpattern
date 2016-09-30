package com.liukai.pattern.structural.bridge;

/**
 * 抽象化（Abstraction）角色：抽象化给出的定义，并保存一个对实现化对象的引用。
 * Created by Administrator on 2016/9/30 0030.
 */
public abstract class Abstraction {

    protected Implementor imp;


    /**
     * 商业方法
     */
    public void operation(){
        imp.operationImp();
    }
}


