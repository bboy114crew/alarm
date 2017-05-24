package com.thangnv.fu.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;

import com.thangnv.fu.R;
import com.thangnv.fu.listener.OnSaveAlarmListener;
import com.thangnv.fu.utils.LogUtil;
import com.thangnv.fu.utils.Util;

import java.util.Calendar;

import static com.thangnv.fu.common.Constants.STATE_ADD;
import static com.thangnv.fu.common.Constants.STATE_EDIT;

/**
 * Created by ll on 5/16/2017.
 */

public class AlarmDialog extends Dialog {
    private static final String TAG = "AlarmDialog";

    private TextView btnCancel;
    private TextView btnSave;
    private TimePicker timePicker;
    private String time;
    private int position;
    private OnSaveAlarmListener mOnSaveAlarmListener;
    private int state = STATE_ADD;

    public AlarmDialog(Context activity, OnSaveAlarmListener mOnSaveAlarmListener) {
        super(activity);
        this.mOnSaveAlarmListener = mOnSaveAlarmListener;
    }

    public AlarmDialog(Context activity, String time, int position, OnSaveAlarmListener mOnSaveAlarmListener) {
        super(activity);
        this.time = time;
        this.position = position;
        this.mOnSaveAlarmListener = mOnSaveAlarmListener;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_alarm);

        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnSave = (TextView) findViewById(R.id.btn_save);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        if (time != null) {
            String[] parts = time.split(":");
            int newHour = Integer.parseInt(parts[0]);
            int newMinutes = Integer.parseInt(parts[1]);
            state = STATE_EDIT;
            timePicker.setIs24HourView(true);
            int currentApiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                timePicker.setHour(newHour);
                timePicker.setMinute(newMinutes);
            } else {
                timePicker.setCurrentHour(newHour);
                timePicker.setCurrentMinute(newMinutes);
            }

        } else {
            Calendar cal = Calendar.getInstance();
            time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
            time = Util.setTimeAlarm(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
        }
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeChanged: " + hourOfDay + ":" + minute);
                time = Util.setTimeAlarm(hourOfDay, minute);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnSaveAlarmListener.onSaveSuccess(time, position, state);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(TAG, "Click cancel button");
                dismiss();
            }
        });
    }
}
