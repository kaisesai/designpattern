package com.liukai.pattern.create.multitonpattern;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 多例模式与多语言
 * Created by Administrator on 2016/7/25 0025.
 */
public class LingualResourceTester {

    public static void main(String[] args) {
        LingualResource us = LingualResource.getInstance("en", "US");
        System.out.println(us.getLocaleString("USD"));
        LingualResource cn = LingualResource.getInstance("zh", "CN");
        System.out.println(cn.getLocaleString("USD"));

    }
}

/**
 * 语言资源类（多例类）
 */
class LingualResource {

    private String localeCode;
    private static final String FILE_NAME = "res";
    private static Map<String, LingualResource> instances = new HashMap<>();

    private Locale locale;
    private ResourceBundle resourceBundle;


    private LingualResource(String language, String country) {
        locale = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle(FILE_NAME, locale);
        instances.put(makeLocaleCode(language, country), this);
    }


    /**
     * 获取本地代码值
     *
     * @param code
     * @return
     */
    public String getLocaleString(String code) {
        return resourceBundle.getString(code);
    }

    /**
     * 静态工厂方法
     *
     * @param language
     * @param country
     * @return
     */
    public static synchronized LingualResource getInstance(String language, String country) {
        String localeCode = makeLocaleCode(language, country);
        if (instances.containsKey(localeCode)) {
            return instances.get(localeCode);
        } else {
            return new LingualResource(language, country);
        }
    }

    /**
     * 辅助方法：生成语言国家码
     *
     * @param language
     * @param country
     * @return
     */
    private static String makeLocaleCode(String language, String country) {
        return language + "_" + country;
    }

}
