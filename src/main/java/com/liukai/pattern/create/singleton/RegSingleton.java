package com.liukai.pattern.create.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 登记式单例模式（克服饿汉式和懒汉式单例模式无法继承的问题）
 * Created by Administrator on 2016/7/21 0021.
 */
public class RegSingleton {

    private static Map<String, Object> m_registry = new HashMap<>();

    /*
        初始化时将创建自身实例并注册到map中
     */
    static {
        RegSingleton regSingleton = new RegSingleton();
        m_registry.put(regSingleton.getClass().getName(), regSingleton);
    }

    /**
     * 受保护的构造器
     */
    protected RegSingleton() {
    }

    /**
     * 静态工厂方法，返回此类唯一实例
     *
     * @param name
     * @return
     */
    public static RegSingleton getInstance(String name) {
        if (name == null) {
            name = RegSingleton.class.getName();
        }
        if (m_registry.get(name) == null) {
            try {
                m_registry.put(name, Class.forName(name).newInstance());
            } catch (Exception e) {
                System.out.println("Error  happened");
            }
        }
        return (RegSingleton) m_registry.get(name);
    }

    /**
     * 一个示意性的商业方法
     *
     * @return
     */
    public String abort() {
        return "Hello I am RegSingleton.";
    }

    /**
     * 登记类单例子类
     */
    public static class RegSingletonChild extends RegSingleton {

        public RegSingletonChild() {

        }

        public static RegSingletonChild getInstance() {
            return (RegSingletonChild) RegSingleton.getInstance(RegSingletonChild.class.getName());
        }

        /**
         * 一个示意性的商业方法
         *
         * @return
         */
        public String abort() {
            return "Hello I am RegSingletonChild.";
        }
    }
}
