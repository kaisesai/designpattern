package com.liukai.pattern.structural.adapter;

/**
 * 缺省适配器模式————鲁智深的故事
 * “此人上应天星，心地刚直。虽然时下凶顽，命中驳杂，久后却得清净。正果非凡，汝等皆不及他。”
 * Created by Administrator on 2016/8/10 0010.
 */
public class DefaultAdapter {

    public static void main(String[] args) {
        Monk LuZhiShen = new LuZhiShen();
        String name = LuZhiShen.getName();
        System.out.println(name);
        LuZhiShen.practiseWuShu();
    }
}

/**
 * 和尚
 */
interface Monk {

    /**
     * 吃斋
     */
    void eat();

    /**
     * 念经
     */
    void recite();

    /**
     * 打坐
     */
    void meditation();

    /**
     * 撞钟
     */
    void toll();

    /**
     * 习武
     */
    void practiseWuShu();

    String getName();

}

/**
 * 缺省适配器类————天星
 */
abstract class Star implements Monk {

    @Override
    public void eat() {

    }

    @Override
    public void recite() {

    }

    @Override
    public void meditation() {

    }

    @Override
    public void toll() {

    }

    @Override
    public void practiseWuShu() {

    }

    @Override
    public String getName() {
        return null;
    }
}

/**
 * 鲁智深
 */
class LuZhiShen extends Star {

    @Override
    public void practiseWuShu() {
        System.out.println("拳打镇关西");
        System.out.println("大闹五台山");
        System.out.println("大闹桃花村");
        System.out.println("火烧瓦罐寺");
        System.out.println("倒拔垂杨柳");
    }

    @Override
    public String getName() {
        return "鲁智深";
    }
}

