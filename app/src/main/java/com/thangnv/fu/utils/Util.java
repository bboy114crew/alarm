package com.thangnv.fu.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.thangnv.fu.AlarmReceiver;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by ll on 5/22/2017.
 */

public class Util {

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

    public static long convertTime(String time) {
        Calendar cal = Calendar.getInstance();
        String[] parts = time.split(":");
        int newHour = Integer.parseInt(parts[0]);
        int newMinutes = Integer.parseInt(parts[1]);
        cal.set(Calendar.HOUR, newHour);
        cal.set(Calendar.MINUTE, newMinutes);
        long difference = cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        return difference;
    }

    public static void ringAlarm(Context context, int[] a){
        AlarmManager mgrAlarm = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<>();

        for(int i = 0; i < a.length; ++i)
        {
            Intent intent = new Intent(context, AlarmReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, a[i], intent, 0);

            mgrAlarm.set(AlarmManager.RTC_WAKEUP,
                    SystemClock.elapsedRealtime() + a[i],
                    pendingIntent);
            intentArray.add(pendingIntent);
        }
    }
}
