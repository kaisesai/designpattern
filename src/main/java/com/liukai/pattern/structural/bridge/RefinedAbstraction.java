package com.liukai.pattern.structural.bridge;

/**
 * 修正抽象化（Refined Abstraction）角色：扩展抽象化角色，改变和修正父类对抽象化的定义。
 * Created by Administrator on 2016/9/30 0030.
 */
public class RefinedAbstraction extends Abstraction {

    /**
     * 某个商业方法在修正抽象化角色的实现
     */
    @Override
    public void operation() {
        //重写逻辑代码
        super.operation();
    }
}
