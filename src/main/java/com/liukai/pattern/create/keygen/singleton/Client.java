package com.liukai.pattern.create.keygen.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式————有缓存的多序列键生成器
 * Created by Administrator on 2016/7/27 0027.
 */
public class Client {

    public static void main(String[] args) {

        KeyGenerator keyGenerator = KeyGenerator.getInstance();
        for (int i = 0; i < 20; i++) {
            int empNum = keyGenerator.getNextKey("empNum");
            System.out.println("empNum的键的序列值为："+empNum);
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            int stuNum = keyGenerator.getNextKey("stuNum");
            System.out.println("stuNum的键的序列值为："+stuNum);
        }
    }
}

/**
 * 有缓存的多序列键生成器
 * 单例类内聚了内蕴状态
 */
class KeyGenerator {

    private Map<String, KeyInfo> keyInfoCache = new HashMap<>();
    private static KeyGenerator keyGenerator = new KeyGenerator();
    private static final int POOL_SIZE = 20;//键缓存区

    private KeyGenerator() {
    }

    /**
     * 静态工厂方法————懒汉式单例
     *
     * @return
     */
    public static synchronized KeyGenerator getInstance() {
        return keyGenerator;
    }

    public int getNextKey(String keyName) {
        KeyInfo keyInfo;
        if (keyInfoCache.containsKey(keyName)) {
            keyInfo = keyInfoCache.get(keyName);
        } else {
            keyInfo = new KeyInfo(keyName, POOL_SIZE);
            keyInfoCache.put(keyName, keyInfo);
        }
        return keyInfo.getNextKey();
    }

}

/**
 * 键信息
 */
class KeyInfo {

    private int poolSize;
    private String keyName;
    private int keyMin;
    private int keyMax;
    private int nextKey;

    public KeyInfo(String keyName, int poolSize) {
        this.keyName = keyName;
        this.poolSize = poolSize;
        retrieveFromDB();
    }

    public int getNextKey() {
        if (nextKey > keyMax) {
            retrieveFromDB();
        }
        return nextKey++;
    }


    /**
     * 内部方法，从数据库提取键的当前值
     */
    private void retrieveFromDB() {
        String sql1 = "update keyTable set keyValue=keyValue+" + poolSize + " where keyName=" + keyName;
        String sql2 = "select keyValue from keyTable where keyName=" + keyName;
        //执行事务以及提交
        //假设返回1000
        int keyFromDB = 1000;
        keyMax = keyFromDB;
        keyMin = keyFromDB - poolSize + 1;
        nextKey = keyMin;
    }

}


