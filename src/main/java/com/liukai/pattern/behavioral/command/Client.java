package com.liukai.pattern.behavioral.command;

/**
 * 命令模式
 * 描述：
 * Created by Administrator on 2016/12/30 0030.
 */
public class Client {

    public static void main(String[] args) {

        //创建接收者对象
        Receiver receiver = new Receiver();

        //创建命令对象
        ConcreteCommand concreteCommand = new ConcreteCommand(receiver);

        //创建请求者对象
        Invoker invoker = new Invoker(concreteCommand);

        //执行命令
        invoker.action();
    }

}

/**
 * 请求者角色
 * 描述：负责调用命令对象执行一个请求，相关的方法叫做行动方法。
 */
class Invoker {

    /**
     * 命令对象
     */
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    /**
     * 行动方法
     */
    public void action() {
        System.out.println("Invoker action begin...");
        command.execute();
    }
}

/**
 * 命令角色
 * 描述：声明一个所有具体命令类的抽象接口。这是一个抽象角色，通常由一个Java接口或者抽象类来实现。
 */
interface Command {

    /**
     * 执行方法
     */
    void execute();
}

/**
 * 具体命令角色
 * 描述：定义一个接收者和行为之间的弱耦合；实现execute()方法，负责调用接收者的相应操作。execute()方法通常叫做执行方法。
 */
class ConcreteCommand implements Command {

    /**
     * 接收者角色
     */
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 执行方法
     */
    @Override
    public void execute() {
        System.out.println("Command execute begin...");
        receiver.action();
    }
}

/**
 * 接收者角色
 * 描述：负责具体实施和执行一个请求。任何一个类都可以成为接收者，实施和执行请求的方法叫做行动方法。
 */
class Receiver {

    /**
     * 行动方法
     */
    public void action() {
        System.out.println("Receiver action begin...");
    }
}