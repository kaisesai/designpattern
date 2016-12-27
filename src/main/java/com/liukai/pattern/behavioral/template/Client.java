package com.liukai.pattern.behavioral.template;

/**
 * 模板方法
 * Created by Administrator on 2016/12/27 0027.
 */
public class Client {

    public static void main(String[] args) {
        AbstractClass concreteClass = new ConcreteClass();
        //执行模板方法
        concreteClass.templateMethod();
    }
}

/**
 * 抽象模板角色
 */
abstract class AbstractClass {

    /**
     * 模板方法
     * 作为顶级逻辑的骨架，而逻辑组成步骤在相应的抽象操作中，推迟到子类是实现。也有可能调用一些具体方法。
     */
    public void templateMethod() {
        //调用基本方法（由子类实现）
        hookMethod();
        //调用基本方法（有子类实现）
        doOperation1();
        //调用基本方法（已经实现）
        doOperation2();
    }

    /**
     * 基本方法
     * 钩子方法：一个钩子方法由抽象类提供并实现，并由子类去扩展。通常情况下抽象类给出的是一个空实现，作为默认的钩子方法
     */
    public void hookMethod() {
    }

    /**
     * 基本方法
     * 抽象方法：由子类去实现
     */
    public abstract void doOperation1();

    /**
     * 基本方法
     * 具体方法：已经实现
     */
    public void doOperation2() {
        System.out.println("开始执行操作...");
    }
}

/**
 * 具体模板类
 */
class ConcreteClass extends AbstractClass {

    /**
     * 基本方法的实现
     * 钩子方法
     */
    @Override
    public void hookMethod() {
        System.out.println("执行了子类的钩子方法...");
    }

    /**
     * 基本方法的实现
     */
    @Override
    public void doOperation1() {
        System.out.println("执行具体子类的基本方法...");
    }

}
