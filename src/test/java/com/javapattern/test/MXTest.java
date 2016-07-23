package com.javapattern.test;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * MX测试类
 * Created by Administrator on 2016/7/23 0023.
 */
public class MXTest {

    public static void main(String[] args) {

        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");//设置服务的名称
            // env.put("", "");//设置服务的URL

            //创建环境对象
            InitialDirContext initialDirContext = new InitialDirContext(env);

            //读取环境对象的属性
            Attributes attributes = initialDirContext.getAttributes("baidu.com", new String[]{"MV"});

            for (NamingEnumeration<? extends Attribute> ae = attributes.getAll(); ae != null && ae.hasMoreElements(); ) {
                Attribute attr = ae.next();
                NamingEnumeration<?> e = attr.getAll();
                while (e.hasMoreElements()) {
                    Object o = e.nextElement();
                    System.out.println(o);
                }
            }
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }

    }
}
