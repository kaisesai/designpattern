package com.liukai.pattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态代理例子
 * Created by Administrator on 2016/9/10 0010.
 */
public class DynamicDemo {

    public static void main(String[] args) {
        List<String> list = (List<String>) ListProxy.factory(new ArrayList<String>());
        list.add("a");
    }


    public static class ListProxy implements InvocationHandler {

        private Object proxyobj;

        public ListProxy(Object obj) {
            this.proxyobj = obj;
        }

        public static Object factory(Object obj) {
            Class<?> clazz = obj.getClass();
            return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ListProxy(obj));
        }

        /**
         * @param proxy  动态代理对象，一般情况下在此方法中没有用
         * @param method 执行的方法
         * @param args   执行方法所传入的参数
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(Arrays.toString(proxy.getClass().getInterfaces()));
            System.out.println("输出代理类结构");
            Class<?> proxyClass = proxy.getClass();

            System.out.println("包名:" + proxyClass.getPackage());
            System.out.println("类修饰符：" + Modifier.toString(proxyClass.getModifiers()));
            System.out.println("类名:" + proxyClass.getClass());
            System.out.println("父类：" + proxyClass.getSuperclass());
            System.out.println("接口：" + Arrays.toString(proxyClass.getInterfaces()));
            System.out.println("声明的字段：" + Arrays.toString(proxyClass.getDeclaredFields()));
            System.out.println("公共的字段：" + Arrays.toString(proxyClass.getFields()));
            System.out.println("声明的方法：" + Arrays.toString(proxyClass.getDeclaredMethods()));
            System.out.println("公共的方法：" + Arrays.toString(proxyClass.getMethods()));

            System.out.println("方法执行之前: proxy=" + proxy.getClass() + " method=" + method + " args=" + Arrays.toString(args));
            Object invoke = method.invoke(proxyobj, args);
            return invoke;
        }
    }
}
