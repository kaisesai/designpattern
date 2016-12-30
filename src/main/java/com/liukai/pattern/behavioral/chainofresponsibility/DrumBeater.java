package com.liukai.pattern.behavioral.chainofresponsibility;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import static com.liukai.pattern.behavioral.chainofresponsibility.DrumBeater.random;

/**
 * 责任链模式之击鼓传花
 * 使用两个线程执行程序，一个是击鼓者线程，只负责击鼓，另一个是传花者线程（主线程），只负责传花
 * Created by Administrator on 2016/12/28 0028.
 */
public class DrumBeater {

    static boolean stopped = false;
    Timer timer = new Timer();
    public static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) throws InterruptedException {

        //创建击鼓者对象
        DrumBeater drumBeater = new DrumBeater();

        //创建传花者对象
        ConcretePlayer jiaMu = new ConcretePlayer("JiaMu");
        ConcretePlayer jiaZhen = new ConcretePlayer("JiaZhen");
        ConcretePlayer jiaBaoYu = new ConcretePlayer("JiaBaoYu");
        ConcretePlayer jiaShe = new ConcretePlayer("JiaShe");
        ConcretePlayer jiaHuan = new ConcretePlayer("JiaHuan");

        //设置责任链
        jiaMu.setSuccessor(jiaZhen);
        jiaZhen.setSuccessor(jiaBaoYu);
        jiaBaoYu.setSuccessor(jiaShe);
        jiaShe.setSuccessor(jiaHuan);
        jiaHuan.setSuccessor(jiaMu);

        //开始击鼓
        int time = (int) (Math.random() * 10);
        System.out.println("击鼓时间为：" + time);
        drumBeater.startBeating(time);

        //开始传花
        jiaMu.handle();
    }

    /**
     * 开始击鼓
     *
     * @param stopInSeconds 延迟的时间
     */
    public void startBeating(int stopInSeconds) {
        System.out.println("Drum beating started...");
        timer.schedule(new StopBeatingReminder(), stopInSeconds * 1000);
    }

    private class StopBeatingReminder extends TimerTask {

        @Override
        public void run() {
            System.out.println("Drum beating stopped...");
            stopped = true;
            timer.cancel();
        }
    }


}

/**
 * 抽象处理者角色
 * 描述：抽象传花者
 */
abstract class Player {

    /**
     * 下一个处理者
     */
    private Player successor;

    /**
     * 抽象方法，处理请求方法
     */
    public abstract void handle();

    /**
     * 描述：把花传递给下一家
     */
    public void next() {
        if (successor != null) {
            successor.handle();
        } else {
            System.out.println("Program is terminating.");
        }
    }

    public Player getSuccessor() {
        return successor;
    }

    public void setSuccessor(Player successor) {
        this.successor = successor;
    }
}

/**
 * 第一个传花者
 */
class ConcretePlayer extends Player {

    private String name;

    public ConcretePlayer(String name) {
        this.name = name;
    }

    /**
     * 执行请求
     */
    @Override
    public void handle() {
        try {
            long time = random.nextLong(0, 1000);
            //设置睡眠的作用：1.模拟真实场景中不同的人传递花的速度 2.防止栈内存溢出（主要作用）
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
        if (DrumBeater.stopped) {
            System.out.println(name + " gotta drink!");
        } else {
            System.out.println(name + " passed!");
            //将花传递给下家
            next();
        }
    }
}