package com.liukai.pattern.behavioral.memento.whitebox;

/**
 * “白箱”备忘录模式的实现
 * Created by Administrator on 2017/1/6 0006.
 */
public class Client {

    private static Originator originator = new Originator();
    private static Caretaker caretaker = new Caretaker();

    public static void main(String[] args) {
        //改变发起人的状态
        originator.setState("on");
        //创建备忘录对象，并将发起人对象的状态存储起来
        caretaker.setMemento(originator.createMemento());
        //修改发起人对象的状态
        originator.setState("off");
        //恢复发起人对象的状态
        originator.restoreMemento(caretaker.retrieveMemento());
    }
}

/**
 * 发起人角色
 * 描述：1.创建一个含有当前状态的备忘录对象。2.使用备忘录对象存储其内部状态。
 */
class Originator {

    private String state;

    /**
     * 工厂方法，返回一个新的备忘录对象
     *
     * @return
     */
    public Memento createMemento() {
        return new Memento(state);
    }

    /**
     * 将发起人恢复到备忘录对象所记载的状态
     *
     * @param memento
     */
    public void restoreMemento(Memento memento) {
        state = memento.getState();
    }

    /**
     * 取值方法
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * 赋值方法
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
        System.out.println("Current state =" + this.state);
    }
}

/**
 * 备忘录角色
 * 描述：1.将发起人对象的内部状态存储起来。备忘录可以根据发起人对象判断来决定存储多少发起人对象的内部状态。2.备忘录可以保护其内容不被发起人对象之外的任何对象所读取。
 */
class Memento {

    /**
     * 状态
     */
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 负责人角色
 * 描述：1.负责保存备忘录对象。
 * ● 不检查备忘录对象的内容。
 */
class Caretaker {

    private Memento memento;

    /**
     * 备忘录的取值方法
     *
     * @return
     */
    public Memento retrieveMemento() {
        return memento;
    }

    /**
     * 备忘录的赋值方法
     *
     * @param memento
     */
    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
