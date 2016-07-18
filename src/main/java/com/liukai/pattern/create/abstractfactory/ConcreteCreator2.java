package com.liukai.pattern.create.abstractfactory;

/**
 * 具体工厂角色2
 * Created by Administrator on 2016/7/18 0018.
 */
public class ConcreteCreator2 implements Creator {
    public ProductA factoryA() {
        return new ConcreteProductA2();
    }

    public ProductB factoryB() {
        return new ConcreteProductB2();
    }
}
