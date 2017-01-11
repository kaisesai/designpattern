package com.liukai.pattern.behavioral.state;

/**
 * 状态模式
 * 描述：状态模式允许一个对象在其内部状态改变的时候改变其行为。这个对象看上去就像是改变了它的类一样。
 * Created by Administrator on 2017/1/11 0011.
 */
public class Client {

    public static void main(String[] args) {

        //创建环境类角色
        Context context = new Context();
        context.setState(new ConcreteState());
    }
}

/**
 * 类名：环境类角色
 * 描述：定义客户端所感兴趣的接口，并且保留一个具体状态的实例，这个具体状态类的实例给出此环境对象的现有状态。
 */
class Context {

    private State state;

    public void sampleOperation() {
        state.sampleOperation();
    }

    public void setState(State state) {
        this.state = state;
    }
}

/**
 * 接口名：抽象状态角色
 * 描述：定义一个接口，用以封装环境对象的一个特定的状态所对应的行为。
 */
interface State {

    /**
     * 接口名：定义状态的行为方法
     */
    void sampleOperation();
}

/**
 * 类名：具体状态角色
 * 描述：每一个具体状态类都实现了环境的一个状态所对应的行为。
 */
class ConcreteState implements State {

    @Override
    public void sampleOperation() {
        System.out.println("invoke this state...");
    }

}
