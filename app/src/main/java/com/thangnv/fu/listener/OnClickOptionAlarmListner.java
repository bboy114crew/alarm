package com.thangnv.fu.listener;

import android.view.View;

import java.util.HashMap;

/**
 * Created by ll on 5/26/2017.
 */

public interface OnClickOptionAlarmListner {
    void addDayRepeate (View view, HashMap<String, Boolean> dayRepeate);
    boolean[] getListDayRepeate();
}
