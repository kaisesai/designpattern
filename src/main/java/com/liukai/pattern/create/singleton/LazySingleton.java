package com.liukai.pattern.create.singleton;

/**
 * 懒汉式单例模式
 * Created by Administrator on 2016/7/21 0021.
 */
public class LazySingleton {

    private static LazySingleton lazySingleton;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
