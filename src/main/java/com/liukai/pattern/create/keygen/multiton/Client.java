package com.liukai.pattern.create.keygen.multiton;

import java.util.HashMap;
import java.util.Map;

/**
 * 多例模式————有缓存的多序列键生成器
 * Created by Administrator on 2016/7/27 0027.
 */
public class Client {

    public static void main(String[] args) {

        KeyGenerator empNumKeyGene = KeyGenerator.getInstance("empNum");
        for (int i = 0; i < 20; i++) {
            System.out.println("empNum的序列值为：" + empNumKeyGene.getNextKey());
        }

        System.out.println();
        KeyGenerator stuNumKeyGene = KeyGenerator.getInstance("stuNum");
        for (int i = 0; i < 20; i++) {
            System.out.println("stuNum的序列值为：" + stuNumKeyGene.getNextKey());
        }
    }
}

/**
 * 有缓存的多序列键生成器
 * 多例类模式，内聚了多列类的实例
 */
class KeyGenerator {

    private static Map<String, KeyGenerator> keyGenerators = new HashMap<>();
    private static final int POOL_SIZE = 20;
    private KeyInfo keyInfo;

    private KeyGenerator(String keyName) {
        keyInfo = new KeyInfo(keyName, POOL_SIZE);
    }

    /**
     * 静态工厂方法————多例模式
     *
     * @param keyName
     * @return
     */
    public static synchronized KeyGenerator getInstance(String keyName) {
        KeyGenerator keyGenerator;
        if (keyGenerators.containsKey(keyName)) {
            keyGenerator = keyGenerators.get(keyName);
        } else {
            keyGenerator = new KeyGenerator(keyName);
            keyGenerators.put(keyName, keyGenerator);
        }
        return keyGenerator;
    }

    public int getNextKey() {
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
