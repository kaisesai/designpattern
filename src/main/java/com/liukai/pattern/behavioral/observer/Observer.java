package com.liukai.pattern.behavioral.observer;

/**
 * 抽象观察者角色
 * 为所有的具体观察者角色定义一个接口，在得到主题的通知时更新自己。这个接口叫做更新接口
 *
 */
public interface Observer {

    void update();
}
