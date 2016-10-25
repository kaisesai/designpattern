package com.liukai.pattern.behavioral.strategy;

/**
 * 环境角色
 * 持有一个Strategy类的引用
 * Created by Administrator on 2016/10/25 0025.
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

}
