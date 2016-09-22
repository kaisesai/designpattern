package com.liukai.pattern.create.singleton;

/**
 * 饿汉式单例模式
 * Created by Administrator on 2016/7/21 0021.
 */
public class EagerSingleton {

    private static EagerSingleton eagerSingleton = new EagerSingleton();

    private EagerSingleton() {

    }

    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }

}
