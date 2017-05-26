package com.thangnv.fu.utils;

import android.util.Log;

/**
 * Created by ll on 5/22/2017.
 */

public class LogUtil {

    public static final boolean isShowLog = true;

    public static final String TAG = "LogUtil";

    public static void d(String TAG,String message){
        if(isShowLog) {
            Log.d(TAG, message);
        }
    }
}
