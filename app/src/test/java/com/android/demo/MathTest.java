package com.android.demo;

import org.junit.Test;

import java.util.Calendar;

/**
 * <p>
 *
 * @author anlc
 * @date 2019-11-20
 */
public class MathTest {

    @Test
    public void mathTest() {

        Calendar calendar = Calendar.getInstance();

        int r = 50;

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.println("hour:" + hour);
        int degree = 360 / 12 * hour;

        double radian = Math.toRadians(degree);
        int endx = (int) (r * 0.5 * Math.cos(radian));
        int endy = (int) (r * 0.5 * Math.sin(radian));

        System.out.println("degree:" + degree);
        System.out.println("radian:" + radian);
        System.out.println("endx:" + endx);
        System.out.println("endy:" + endy);
    }
}
