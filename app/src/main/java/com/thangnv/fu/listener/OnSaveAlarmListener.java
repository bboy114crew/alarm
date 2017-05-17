package com.thangnv.fu.listener;

/**
 * Created by ll on 5/17/2017.
 */

public interface OnSaveAlarmListener {
    public static final int STATE_EDIT = 1;
    public static final int STATE_ADD = 2;

    void onCancel();

    void onSaveSuccess(int position, int state);
}

