package com.thangnv.fu.listener;

/**
 * Created by ll on 5/17/2017.
 */

public interface OnSaveAlarmListener {


    void onCancel();

    void onSaveSuccess(String time,int position, int state);

    void onDelete();
}

