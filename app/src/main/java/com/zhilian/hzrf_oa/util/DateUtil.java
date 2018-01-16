package com.zhilian.hzrf_oa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018-1-2.
 */
public class DateUtil {
    public static DateUtil instance = null;

    private SimpleDateFormat sdf = null;


    private DateUtil(){

    }

    public static synchronized DateUtil getInstance() {
        if (null == instance){
            synchronized (DateUtil.class){
                instance = new DateUtil();
            }
        }
        return instance;
    }


    public Date str2Date(String strDate){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public double diffDays(String date2, String date1){
        return (int) ((str2Date(date2).getTime() - str2Date(date1).getTime()) / (1000*3600*24));
    }
    public int[] splitDate(String strDate){
        String[]  arr = strDate.split("-");
        int[] dates = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dates[i] = Integer.valueOf(arr[i].trim());
        }
        return dates;
    }
}
