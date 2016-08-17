package com.liukai.pattern.structural.composite;

import java.util.LinkedList;
import java.util.List;

/**
 * 安全式的合成模式
 * Created by Administrator on 2016/8/17 0017.
 */
public class SafeComposite {
    public static void main(String[] args) {

        Composite composite = new Composite();
        for (int i = 0; i < 10; i++) {
            composite.add(new Leaf());
        }
        composite.sameOperation();
    }

    /**
     * 抽象构件角色
     * 用于给参与合成的对象公共的接口和默认的实现
     */
    interface Component {

        /**
         * 返回自己的实例
         *
         * @return
         */
        Composite getComposite();

        /**
         * 某个商业方法
         */
        void sameOperation();

    }

    /**
     * 树叶构件角色
     * 没有子节点对象
     */
    static class Leaf implements Component {

        @Override
        public Composite getComposite() {
            return null;
        }

        @Override
        public void sameOperation() {
            System.out.println("Leaf");
        }
    }

    /**
     * 树枝构件角色
     * 可以拥有子对象
     */
    static class Composite implements Component {

        private List<Component> components = new LinkedList<>();

        /**
         * 管理聚集方法，增加一个子构件对象
         *
         * @param component
         */
        public void add(Component component) {
            this.components.add(component);
        }

        /**
         * 管理聚集方法，删除一个子构件对象
         *
         * @param component
         */
        public void remove(Component component) {
            components.remove(component);
        }

        /**
         * 管理聚集方法，返回聚集对象
         *
         * @return
         */
        public List<Component> components() {
            return components;
        }


        @Override
        public Composite getComposite() {
            return this;
        }

        @Override
        public void sameOperation() {
            for (Component component : components) {
                component.sameOperation();
            }
        }

    }
}




