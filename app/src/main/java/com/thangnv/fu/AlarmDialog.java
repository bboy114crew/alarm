package com.thangnv.fu;

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

/**
 * Created by ll on 5/16/2017.
 */

public class AlarmDialog extends Dialog {
    private static final String TAG = "AlarmDialog";

    private TextView btnCancel;
    private TextView btnSave;
    private TimePicker timePicker;
    private String time;

    public AlarmDialog(Context activity) {
        super(activity);
    }

    public AlarmDialog(Context activity, String time, int position) {
        super(activity);
        this.time = time;
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

        //timePicker.setIs24HourView(true);
        String[] parts = time.split(":");
        int newHour = Integer.parseInt(parts[0]);
        int newMinutes = Integer.parseInt(parts[1]);
        if (time != null) {
            timePicker.setCurrentHour(newHour);
            timePicker.setCurrentMinute(newMinutes);
        }
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeChanged: " + hourOfDay + ":" + minute);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {

                }
            }
        });
    }
}
