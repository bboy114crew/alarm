package com.thangnv.fu.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.thangnv.fu.R;
import com.thangnv.fu.listener.OnClickOptionAlarmListner;
import com.thangnv.fu.listener.OnSaveAlarmListener;
import com.thangnv.fu.utils.LogUtil;
import com.thangnv.fu.utils.Util;

import java.util.Calendar;
import java.util.HashMap;

import static com.thangnv.fu.common.Constants.STATE_ADD;
import static com.thangnv.fu.common.Constants.STATE_EDIT;

/**
 * Created by ll on 5/16/2017.
 */

public class AlarmDialog extends Dialog implements View.OnClickListener,OnClickOptionAlarmListner{
    private static final String TAG = "AlarmDialog";

    private TextView btnCancel;
    private TextView btnSave;
    private TimePicker timePicker;
    private TextView btnDelete;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private RelativeLayout relativeLayout3;
    private RelativeLayout relativeLayout4;
    private Switch swStateSnoodze;
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
        btnDelete = (TextView) findViewById(R.id.deleteAlarm);
        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnSave = (TextView) findViewById(R.id.btn_save);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        relativeLayout3 = (RelativeLayout) findViewById(R.id.relativeLayout3);
        relativeLayout4 = (RelativeLayout) findViewById(R.id.relativeLayout4);
        swStateSnoodze = (Switch)  findViewById(R.id.sw_state_snoodze);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        relativeLayout3.setOnClickListener(this);
        relativeLayout4.setOnClickListener(this);
        swStateSnoodze.setOnClickListener(this);
        btnDelete.setVisibility(View.INVISIBLE);
        if (time != null) {
            String[] parts = time.split(":");
            int newHour = Integer.parseInt(parts[0]);
            int newMinutes = Integer.parseInt(parts[1]);
            state = STATE_EDIT;
            btnDelete.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relativeLayout1:
                LogUtil.d(TAG, "Chooose Repeate");
                DayDialog dayDialog= new DayDialog(getContext());
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dayDialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dayDialog.show();
                dayDialog.getWindow().setAttributes(lp);
                break;
            case R.id.relativeLayout2:
                LogUtil.d(TAG, "Choose Label");
                LabelDialog labelDialog = new LabelDialog(getContext());
                WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                lp2.copyFrom(labelDialog.getWindow().getAttributes());
                lp2.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp2.height = WindowManager.LayoutParams.MATCH_PARENT;
                labelDialog.show();
                labelDialog.getWindow().setAttributes(lp2);
                break;
            case R.id.relativeLayout3:
                LogUtil.d(TAG, "Chooose Sound");
                LogUtil.d(TAG, "Choose Label");
                SoundDialog soundDialog = new SoundDialog(getContext());
                WindowManager.LayoutParams lp3 = new WindowManager.LayoutParams();
                lp3.copyFrom(soundDialog.getWindow().getAttributes());
                lp3.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp3.height = WindowManager.LayoutParams.MATCH_PARENT;
                soundDialog.show();
                soundDialog.getWindow().setAttributes(lp3);
                break;
            case R.id.sw_state_snoodze:
                LogUtil.d(TAG, "Switch state of snoodze");
                break;
        }
    }


    @Override
    public void addDayRepeate(View view, HashMap<String, Boolean> dayRepeate) {

    }

    @Override
    public boolean[] getListDayRepeate() {
        return new boolean[0];
    }


}
