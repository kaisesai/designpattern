package com.liukai.pattern.create.abstractfactory;

/**
 * 抽象工厂角色
 * Created by Administrator on 2016/7/18 0018.
 */
public interface Creator {

    /**
     * 产品等级机构A的工厂方法
     *
     * @return
     */
    ProductA factoryA();

    /**
     * 产品等级结构B的工厂方法
     *
     * @return
     */
    ProductB factoryB();
}
