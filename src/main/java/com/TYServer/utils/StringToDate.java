package com.TYServer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanghuibin on 2017/5/12.
 */
public class StringToDate {
    public static Date stringToDate(String time){
        //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
