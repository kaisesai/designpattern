package com.liukai.pattern.behavioral.memento.blackbox;

/**
 * “黑箱”备忘录模式的实现
 * Created by Administrator on 2017/1/7 0007.
 */
public class Client {

    private static Originator originator = new Originator();
    private static Caretaker caretaker = new Caretaker();

    public static void main(String[] args) {
        //修改发起人的状态
        originator.setState("on");
        //创建备忘录角色并保存到负责人角色中
        caretaker.setMemento(originator.createMemento());
        //修改发起人状态
        originator.setState("off");
        //从备忘录角色中恢复状态到发起人角色中
        originator.restoreMemento(caretaker.retrieveMemento());
        System.out.println("Current state= " + originator.getState());
    }
}

/**
 * 类名：发起人角色
 * 描述：负责创建备忘录角色
 */
class Originator {

    /**
     * 状态
     */
    private String state;

    /**
     * 工厂方法：创建备忘录角色
     *
     * @return
     */
    public MementoIF createMemento() {
        return new Memento(state);
    }

    /**
     * 方法名：恢复备忘录角色中的状态到发起人角色
     *
     * @param mementoIF
     */
    public void restoreMemento(MementoIF mementoIF) {
        Memento memento = (Memento) mementoIF;
        this.state = memento.getState();
    }

    /**
     * 获取值方法
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
        System.out.println("Current state= " + state);
    }

    /**
     * 类名：备忘录角色
     * 描述：属于发起人角色的内部类，所有方法都是私有方法，只供自自己访问
     */
    private class Memento implements MementoIF {

        /**
         * 状态，保存的是发起人的状态，此处还可以使用聚集来保存多个发起人状态
         */
        private String state;

        private Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }

        private void setState(String state) {
            this.state = state;
        }
    }
}

/**
 * 接口名：备忘录角色一个窄接口，属于标记接口
 */
interface MementoIF {
}

/**
 * 负责人角色
 */
class Caretaker {

    private MementoIF memento;

    /**
     * 备忘录的取值方法
     *
     * @return
     */
    public MementoIF retrieveMemento() {
        return memento;
    }

    /**
     * 备忘录的赋值方法
     *
     * @param memento
     */
    public void setMemento(MementoIF memento) {
        this.memento = memento;
    }
}

