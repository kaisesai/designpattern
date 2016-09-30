package com.liukai.pattern.structural.bridge;

/**
 * 具体实现化（Concrete Implementor）角色：这个角色给出实现化角色接口的具体实现。
 * Created by Administrator on 2016/9/30 0030.
 */
public class ConcreteImplementor extends Implementor {

    /**
     * 某个商业方法的实现化实现
     */
    @Override
    public void operationImp() {
        System.out.println("Do something...");
    }
}
