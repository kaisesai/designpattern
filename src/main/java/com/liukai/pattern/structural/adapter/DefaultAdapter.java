package com.liukai.pattern.structural.adapter;

/**
 * 缺省适配器模式————鲁智深的故事
 * “此人上应天星，心地刚直。虽然时下凶顽，命中驳杂，久后却得清净。正果非凡，汝等皆不及他。”
 * Created by Administrator on 2016/8/10 0010.
 */
public class DefaultAdapter {

    public static void main(String[] args) {
        和尚 鲁智深 = new 鲁智深();
        String name = 鲁智深.getName();
        System.out.println(name);
        鲁智深.习武();
    }
}

class 鲁智深 extends 天星 {

    @Override
    public void 习武() {
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

/**
 * 缺省适配器类
 */
abstract class 天星 implements 和尚 {

    @Override
    public void 吃斋() {

    }

    @Override
    public void 念经() {

    }

    @Override
    public void 打坐() {

    }

    @Override
    public void 撞钟() {

    }

    @Override
    public void 习武() {

    }

    @Override
    public String getName() {
        return null;
    }
}

/**
 * 和尚
 */
interface 和尚 {

    void 吃斋();

    void 念经();

    void 打坐();

    void 撞钟();

    void 习武();

    String getName();

}
