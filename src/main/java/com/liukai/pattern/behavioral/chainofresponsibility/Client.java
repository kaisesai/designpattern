package com.liukai.pattern.behavioral.chainofresponsibility;

/**
 * 责任链模式
 * 描述：在责任链模式里，很多对象是由每一个对象对其下家的引用而连接在一起形成一条链。请求在这条链上传递，直到链上的某一个对象决定处理此请求。发出这个请求的客户端并不知道链上的请求是由哪一个对象最终处理这个请求，这使得系统可以在不影响客户端的情况下动态地重新组织链和分配责任。
 * Created by Administrator on 2016/12/28 0028.
 */
public class Client {

    public static void main(String[] args) {
        //创建三个处理者对象
        Handler h1 = new ConcreteHandler("handler1");
        Handler h2 = new ConcreteHandler("handler2");
        Handler h3 = new ConcreteHandler("handler3");

        //创建责任链
        //为处理者设置下一个处理者
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);
        // h3.setSuccessor(h1);

        h1.handleRequest();

        System.out.println("停止处理...");

    }
}

/**
 * 抽象处理者角色
 * 描述：定义出一个处理请求接口。如果需要，接口可以定义出一个方法，以设定和返回对下家的引用。这个角色通常由一个java抽象类和java接口来实现，抽象方法handleRequest方法规范了子类处理请求的操作。
 */
abstract class Handler {

    /**
     * 下一个请求对象的引用
     */
    protected Handler successor;

    /**
     * 处理方法，调用此方法处理请求
     */
    public abstract void handleRequest();

    /**
     * 获取下个请求方法
     *
     * @return
     */
    public Handler getSuccessor() {
        return successor;
    }

    /**
     * 赋值方法，调用此设计设定下家
     *
     * @param successor
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}

/**
 * 具体处理者角色
 */
class ConcreteHandler extends Handler {

    private String name;

    public ConcreteHandler(String name) {
        this.name = name;
    }

    /**
     * 处理方法，调用此方法处理请求
     */
    @Override
    public void handleRequest() {
        if (successor != null) {
            System.out.println("The request is passed to " + successor);
            successor.handleRequest();
        } else {
            System.out.println("The request is handled here.");
        }
    }

    @Override
    public String toString() {
        return "ConcreteHandler{" +
                "name='" + name + '\'' +
                '}';
    }
}