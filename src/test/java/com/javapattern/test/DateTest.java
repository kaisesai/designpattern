package com.javapattern.test;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class DateTest {

    @Test
    public void testDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = null;
        try {
            parse = dateFormat.parse("2016-07-18 12:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date = new Date();

        long time = date.getTime() - parse.getTime();
        System.out.println("时间差为：" + time + "ms, " + time / 1000 + "s");

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());

        // Attributes.Name

    }

}
