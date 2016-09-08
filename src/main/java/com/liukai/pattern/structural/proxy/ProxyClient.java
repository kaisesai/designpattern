package com.liukai.pattern.structural.proxy;

/**
 * 代理模式
 * 将客户端的请求转发给真实主题角色之前或之后执行相应的操作
 * Created by Administrator on 2016/9/8 0008.
 */
public class ProxyClient {

    public static void main(String[] args) {
        Subject subject = new ProxySubject();
        subject.request();
    }

    /**
     * 抽象主题角色
     */
    public static abstract class Subject {

        /**
         * 抽象的请求方法
         */
        public abstract void request();
    }

    /**
     * 真实主题角色
     */
    public static class RealSubject extends Subject {
        @Override
        public void request() {
            System.out.println("执行真实主题的请求方法...");
        }
    }

    /**
     * 代理主题角色
     */
    public static class ProxySubject extends Subject {

        private RealSubject realSubject;

        @Override
        public void request() {
            preRequest();
            if (realSubject == null) {
                realSubject = new RealSubject();
            }
            realSubject.request();
            postRequest();
        }

        /**
         * 执行代理操作之前的方法
         */
        private void preRequest() {
            System.out.println("[ProxySubject#preRequest()]执行真实主题对象之前执行的方法...");
        }

        /**
         * 执行代理操作之后的方法
         */
        private void postRequest() {
            System.out.println("[ProxySubject#postRequest()]执行真实主题对象之后执行的方法...");
        }
    }
}
