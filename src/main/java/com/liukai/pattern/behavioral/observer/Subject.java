package com.liukai.pattern.behavioral.observer;

/**
 * 抽象主题角色
 * 主题角色把所有对观察者对象的引用保存在一个聚集里，每一个主题都可以有任意个观察者对象。抽象主题提供一个接口，可以增加和删除观察者对象，主题角色又叫做抽象被观察者（Observable）角色，一般用抽象类或者一个接口来实现
 * Created by Administrator on 2016/11/7 0007.
 */
public interface Subject {

    /**
     * 添加观察者对象
     * @param observer
     */
    void attach(Observer observer);

    /**
     * 移除观察者对象
     * @param observer
     */
    void detach(Observer observer);

    /**
     * 通知所有的观察者对象
     */
    void notifyObservers();

}
