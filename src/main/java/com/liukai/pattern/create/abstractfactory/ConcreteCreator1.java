package com.liukai.pattern.create.abstractfactory;

/**
 * 具体工厂角色1
 * Created by Administrator on 2016/7/18 0018.
 */
public class ConcreteCreator1 implements Creator {
    public ProductA factoryA() {
        return new ConcreteProductA1();
    }

    public ProductB factoryB() {
        return new ConcreteProductB1();
    }
}
