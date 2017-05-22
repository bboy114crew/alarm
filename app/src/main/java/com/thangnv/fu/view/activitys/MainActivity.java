package com.thangnv.fu.view.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thangnv.fu.R;
import com.thangnv.fu.base.BaseActivity;
import com.thangnv.fu.listener.OnSaveAlarmListener;
import com.thangnv.fu.view.dialogs.AlarmDialog;
import com.thangnv.fu.view.fragments.AlarmFragment;
import com.thangnv.fu.view.fragments.ClockFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener, ClockFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";

    private static final int STATE_ALARM = 1;
    private static final int STATE_CLOCK = 2;
    private static final int STATE_WATCH = 3;
    private static final int STATE_TIMER = 4;

    private TextView tvTitle;
    private TextView txtAlarm;
    private TextView txtClock;

    private TextView btnEdit;
    private ImageView btnAdd;
    private LinearLayout viewAlarm;
    private LinearLayout viewWatch;
    private ImageView btnAlarm;
    private ImageView btnClock;
    private Fragment currentFragment;

    private int stateFragment = STATE_CLOCK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewAlarm.setOnClickListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAddBySate(stateFragment);
            }
        });
        viewWatch.setOnClickListener(this);
        changeFragmentByState(stateFragment);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void changeTitle(String title) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_watch:
                Log.d(TAG, "onClick: view_watch");
                stateFragment = STATE_CLOCK;
                changeFragmentByState(stateFragment);
                break;
            case R.id.view_alarm:
                Log.d(TAG, "onClick: view_alarm");
                stateFragment = STATE_ALARM;
                changeFragmentByState(stateFragment);
                break;
            case R.id.btn_edit:
                clickEditByState(stateFragment);

        }
    }
    private void initViews(){
        viewAlarm = (LinearLayout) findViewById(R.id.view_alarm);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnEdit = (TextView) findViewById(R.id.btn_edit);
        btnAdd = (ImageView) findViewById(R.id.btn_add);
        viewWatch = (LinearLayout) findViewById(R.id.view_watch);
        btnAlarm = (ImageView) findViewById(R.id.btn_alarm);
        btnClock = (ImageView) findViewById(R.id.btn_clock);
        txtClock = (TextView) findViewById(R.id.txt_clock);
        txtAlarm = (TextView) findViewById(R.id.txt_alarm);
    }
    private void replaceFragment(Fragment mFragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content, mFragment, tag);
        mFragmentTransaction.commit();
    }

    private void changeFragmentByState(int state) {
        switch (state) {
            case STATE_ALARM:
                currentFragment = AlarmFragment.newInstance();
                replaceFragment(currentFragment, "ALARM_FRAGMENT");
                updateViewByState(state);
                break;
            case STATE_CLOCK:
                currentFragment = ClockFragment.newInstance();
                replaceFragment(currentFragment, "CLOCK_FRAGMENT");
                updateViewByState(state);
                break;
        }
    }

    private void updateViewByState(int state) {
        switch (state) {
            case STATE_CLOCK:
                tvTitle.setText("World Clock");
                btnAlarm.setImageResource(R.drawable.ic_alarm_selector);
                btnClock.setImageResource(R.drawable.ic_lens_selected);
                txtClock.setTextColor(ContextCompat.getColor(this, R.color.selected_color));
                txtAlarm.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                break;
            case STATE_ALARM:
                tvTitle.setText("Alarm");
                btnAlarm.setImageResource(R.drawable.ic_alarm_selected);
                btnClock.setImageResource(R.drawable.ic_lens_selector);
                txtClock.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtAlarm.setTextColor(ContextCompat.getColor(this, R.color.selected_color));
                break;
        }
    }

    private void clickEditByState(int state) {
        switch (state) {
            case STATE_CLOCK:

                break;
            case STATE_ALARM:

                break;
        }
    }

    private void clickAddBySate(int state) {
        switch (state) {
            case STATE_CLOCK:

                break;
            case STATE_ALARM:

                AlarmDialog alarmDialog = new AlarmDialog(this, new OnSaveAlarmListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onSaveSuccess(String time, int position, int state) {
                        if (currentFragment instanceof AlarmFragment) {
                            ((AlarmFragment) currentFragment).onSaveSuccess(time, position, state);
                        }
                    }
                });
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alarmDialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                alarmDialog.show();
                alarmDialog.getWindow().setAttributes(lp);
                break;
        }
    }
}