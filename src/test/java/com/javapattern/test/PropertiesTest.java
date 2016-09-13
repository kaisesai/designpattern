package com.javapattern.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性资源测试类
 * Created by Administrator on 2016/9/13 0013.
 */
public class PropertiesTest {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        //通过类加载器来获取系统资源
        InputStream inputStream = PropertiesTest.class.getClassLoader().getResourceAsStream("log4j.properties");
        properties.load(inputStream);
        System.out.println(properties.toString());
    }
}
