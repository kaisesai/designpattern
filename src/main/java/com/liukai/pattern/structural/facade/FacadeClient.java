package com.liukai.pattern.structural.facade;

/**
 * 门面模式
 * 概念：外部与一个子系统的通信必须通过一个统一的门面（Facade）对象来进行，这就是门面模式
 * Created by Administrator on 2016/9/22 0022.
 */
public class FacadeClient {

    public static void main(String[] args) {

        SecurityFacade facade = new SecurityFacade();
        facade.activate();
        facade.deActivate();
    }

    /**
     * 安全的门面类
     */
    static class SecurityFacade {

        private Camera camera1 = new Camera();
        private Camera camera2 = new Camera();

        private Light light1 = new Light();
        private Light light2 = new Light();
        private Light light3 = new Light();

        private Sensor sensor = new Sensor();
        private Alarm alarm = new Alarm();

        /**
         * 启动保安系统
         */
        public void activate() {
            camera1.turnOn();
            camera2.turnOn();
            light1.turnOn();
            light2.turnOn();
            light3.turnOn();
            sensor.activate();
            alarm.activate();
        }

        /**
         * 关闭保安系统
         */
        public void deActivate() {
            camera1.turnOff();
            camera2.turnOff();
            light1.turnOff();
            light2.turnOff();
            light3.turnOff();
            sensor.deactivate();
            alarm.deactivate();
        }
    }

    /**
     * 录像机
     */
    static class Camera {

        /**
         * 打开录像机
         */
        public void turnOn() {
            System.out.println("Turning on the camera.");

        }

        /**
         * 关闭录像机
         */
        public void turnOff() {
            System.out.println("Turning off the camera.");
        }

        /**
         * 转动录像机
         *
         * @param degrees
         */
        public void rotate(int degrees) {
            System.out.println("Rotating the camera by " + degrees + " degrees");
        }
    }

    /**
     * 灯
     */
    static class Light {

        /**
         * 开灯
         */
        public void turnOn() {
            System.out.println("Turning off the light.");
        }

        /**
         * 关灯
         */
        public void turnOff() {
            System.out.println("Turning off the light.");
        }

        /**
         * 换灯泡
         */
        public void changeBulb() {
            System.out.println("Changing the light-bulb.");
        }

    }

    /**
     * 感应器
     */
    static class Sensor {

        /**
         * 启动感应器
         */
        public void activate() {
            System.out.println("Activate the sensor.");
        }

        /**
         * 关闭感应器
         */
        public void deactivate() {
            System.out.println("Deactivate the sensor.");
        }

        /**
         * 触发感应器
         */
        public void trigger() {
            System.out.println("The sensor has been triggered.");
        }

    }

    /**
     * 警报器
     */
    static class Alarm {

        /**
         * 启动警报器
         */
        public void activate() {
            System.out.println("Activate the alarm.");
        }

        /**
         * 关闭警报器
         */
        public void deactivate() {
            System.out.println("Deactivate the alarm.");
        }

        /**
         * 拉响警报器
         */
        public void ring() {
            System.out.println("Ring the alarm.");
        }

        /**
         * 停掉警报器
         */
        public void stopRing() {
            System.out.println("Stop the alarm.");
        }

    }

}
