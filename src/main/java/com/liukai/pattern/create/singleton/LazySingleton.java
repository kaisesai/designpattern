package com.liukai.pattern.create.singleton;

/**
 * 懒汉式单例模式
 * Created by Administrator on 2016/7/21 0021.
 */
public class LazySingleton {

    private static LazySingleton lazySingleton;
    private String name = "";

    private LazySingleton() {
        try {
            Thread.sleep(1000);
            name = "123";
        } catch (InterruptedException e) {
        }
    }

    /**
     * 建议使用
     *
     * @return
     */
    public static synchronized LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    /**
     * 双重检查成例
     * 注意：如果使用此方法需要将变量设置为volatile的这样才能确保变量在更改后及时被其他线程察觉
     * PS:一般不使用此单例方法
     *
     * @return
     */
    public static LazySingleton getInstanceForDoubleCheck() {
        if (lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

}
