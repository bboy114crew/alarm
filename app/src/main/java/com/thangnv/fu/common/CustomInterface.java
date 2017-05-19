package com.thangnv.fu.common;

/**
 * Created by ll on 5/19/2017.
 */

public class CustomInterface {
    public static String setTimeAlarm(int hour, int minute){
        String time ;
        time = hour + ":" + minute;
        if (hour < 10) {
            time = "0" + hour + ":" + minute;
        }
        if (minute < 10) {
            time = hour + ":" + "0" + minute;
        }
        if (hour < 10 && minute < 10) {
            time = "0" + hour + ":" + "0" + minute;
        }
        return time;
    }

}

