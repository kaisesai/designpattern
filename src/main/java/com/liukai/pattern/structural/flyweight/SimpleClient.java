package com.liukai.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单纯享元模式
 * Created by Administrator on 2016/9/13 0013.
 */
public class  SimpleClient {

    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight a = factory.factory('a');
        a.operation("aa");
        Flyweight b = factory.factory('b');
        b.operation("bb");
        a = factory.factory('a');
        a.operation("cc");
        factory.checkFlyweight();
    }


    /**
     * 享元工厂角色
     * 负责创建与提供享元对象
     */
    public static class FlyweightFactory {

        private Map<Character, Flyweight> files = new HashMap<>();//保存享元对象

        /**
         * 工厂方法
         *
         * @param state 内蕴状态
         * @return 享元对象
         */
        public Flyweight factory(Character state) {
            if (files.containsKey(state)) {
                return files.get(state);
            } else {
                Flyweight flyweight = new ConcreteFlyweight(state);
                files.put(state, flyweight);
                return flyweight;
            }
        }

        /**
         * 辅助方法，检查享元对象
         */
        public void checkFlyweight() {
            int num = 0;
            Set<Map.Entry<Character, Flyweight>> entries = files.entrySet();
            System.out.println("================checkFlyweight()================");
            for (Map.Entry<Character, Flyweight> entry : entries) {
                System.out.println("Item " + (++num) + " : " + entry.getKey());
            }
            System.out.println("================checkFlyweight()================");
        }
    }

    /**
     * 抽象享元角色
     */
    public static abstract class Flyweight {

        /**
         * 示意性的方法，参数为外蕴状态
         *
         * @param state
         */
        public abstract void operation(String state);
    }

    /**
     * 具体享元角色
     */
    public static class ConcreteFlyweight extends Flyweight {

        /**
         * 内蕴状态
         */
        private Character intrinsicState = null;

        public ConcreteFlyweight(Character state) {
            this.intrinsicState = state;
        }

        @Override
        public void operation(String state) {
            System.out.println("内蕴状态为：" + intrinsicState + " 外蕴状态为：" + state);
        }
    }
}
