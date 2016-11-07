package com.liukai.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题角色
 * 将有关状态存入具体主题对象；在具体主题的内部状态改变时，给所有登记过的观察者发出通知。具体主题角色又叫做具体被观察者（Concrete Observable）角色。具体主题角色通常用一个具体子类实现
 * Created by Administrator on 2016/11/7 0007.
 */
public class ConcreteSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    /**
     * 增加一个观察者对象
     * @param observer
     */
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * 移除一个观察者对象
     * @param observer
     */
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有的观察者对象
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
