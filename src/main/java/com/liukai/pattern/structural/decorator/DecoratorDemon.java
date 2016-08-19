package com.liukai.pattern.structural.decorator;

/**
 * 装饰模式
 * Created by Administrator on 2016/8/19 0019.
 */
public class DecoratorDemon {

    public static void main(String[] args) {
        Component concreteComponent = new ConcreteComponent();
        Component concreteDecorator = new ConcreteDecorator(concreteComponent);
        concreteDecorator.sampleOperation();
    }
}

/**
 * 抽象构件角色，用于规范接受附加责任的对象
 */
interface Component {

    /**
     * 商业方法
     */
    void sampleOperation();

}

/**
 * 具体构件角色，定义一个接受附加责任的类
 */
class ConcreteComponent implements Component {

    /**
     * 商业方法
     */
    @Override
    public void sampleOperation() {
        System.out.println("开始执行ConcreteComponent.sampleOperation()...");
    }
}

/**
 * 抽象装饰角色，持有一个构件对象，并定义一个与抽象构件接口一致的接口
 */
class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void sampleOperation() {
        System.out.println("开始委派————抽象装饰角色委派到构件对象");
        component.sampleOperation();
        System.out.println("结束委派————抽象装饰角色委派到构件对象");
    }
}

/**
 * 具体装饰角色，负责给构件对象“附加”责任的类
 */
class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        System.out.println("开始执行具体装饰类...");
        super.sampleOperation();
        System.out.println("结束执行具体装饰类...");
    }
}