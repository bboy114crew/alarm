package com.thangnv.fu.listener;

import com.thangnv.fu.model.AlarmInfo;

/**
 * Created by ll on 5/17/2017.
 */

public interface OnSaveAlarmListener {


    void onCancel();

    void onSaveSuccess(AlarmInfo alarmInfo, int position, int state);

    void onDelete();
}

