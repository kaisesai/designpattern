package com.liukai.pattern.create.prototype;

import java.io.*;
import java.util.Date;

/**
 * 原型模式————深复制之孙大圣的身外身法术
 * 孙大圣类
 * Created by Administrator on 2016/8/3 0003.
 */
public class TheGreatestSage {

    private Monkey monkey = new Monkey();

    public static void main(String[] args) {

        //创建一个孙大圣类
        TheGreatestSage theGreatestSage = new TheGreatestSage();
        //输出大圣本尊类
        System.out.println(theGreatestSage.monkey);
        //执行身外身法术
        Monkey monkeyCopy = theGreatestSage.change();
        System.out.println(monkeyCopy);

        System.out.println("monkey==monkeyCopy: " + theGreatestSage.monkey.equals(monkeyCopy));
        System.out.println("monkey.staff==monkeyCopy.staff: " + theGreatestSage.monkey.getStaff().equals(monkeyCopy.getStaff()));
    }

    /**
     * 身外身法术
     */
    public Monkey change() {
        //深复制
        return monkey.deepClone();
    }
}


/**
 * 大圣本尊类
 */
class Monkey implements Cloneable, Serializable {

    private int height;
    private int weight;
    private GoldRingedStaff staff;
    private Date birthDate;

    public Monkey() {
        this.staff = new GoldRingedStaff();
        this.birthDate = new Date();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public GoldRingedStaff getStaff() {
        return staff;
    }

    public void setStaff(GoldRingedStaff staff) {
        this.staff = staff;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 深复制
     *
     * @return
     */
    public Monkey deepClone() {
        Monkey monkey = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            monkey = (Monkey) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return monkey;
    }

    /**
     * 浅复制
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "height=" + height +
                ", weight=" + weight +
                ", staff=" + staff +
                ", birthDate=" + birthDate + ", " + super.toString() +
                '}';
    }
}

/**
 * 金箍棒类
 */
class GoldRingedStaff implements Serializable {

    private float heigth = 100.0F;//重量
    private float diameter = 10.0F;//直径

    public GoldRingedStaff() {
        //write your code here
    }

    /**
     * 增长行为，每次调用直径和重量增长一倍
     */
    public void grow() {
        this.diameter *= 2.0F;
        this.heigth *= 2.0F;
    }

    /**
     * 缩小行为，每次调用直径和重量缩小一倍
     */
    public void shrink() {
        this.diameter /= 2.0F;
        this.heigth /= 2.0F;

    }

    public float getHeigth() {
        return heigth;
    }

    public void setHeigth(float heigth) {
        this.heigth = heigth;
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "GoldRingedStaff{" +
                "heigth=" + heigth +
                ", diameter=" + diameter + ", " + super.toString() +
                '}';
    }
}