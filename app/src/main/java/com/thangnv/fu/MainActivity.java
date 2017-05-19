package com.thangnv.fu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thangnv.fu.listener.OnSaveAlarmListener;
import com.thangnv.fu.view.AlarmFragment;
import com.thangnv.fu.view.ClockFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ClockFragment.OnFragmentInteractionListener {
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
//        addDataDefaultToDB();
        viewAlarm = (LinearLayout) findViewById(R.id.view_alarm);
        viewAlarm.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnEdit = (TextView) findViewById(R.id.btn_edit);
        btnAdd = (ImageView) findViewById(R.id.btn_add);
        viewWatch = (LinearLayout) findViewById(R.id.view_watch);
        btnAlarm = (ImageView) findViewById(R.id.btn_alarm);
        btnClock = (ImageView) findViewById(R.id.btn_clock);
        txtClock = (TextView) findViewById(R.id.txt_clock);
        txtAlarm = (TextView) findViewById(R.id.txt_alarm);
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

    private void replaceFragment(Fragment mFragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content, mFragment, tag);
        mFragmentTransaction.commit();
    }

    private void changeFragmentByState(int state) {
        switch (state) {
            case STATE_ALARM:
                currentFragment = AlarmFragment.newInstance("", "");
                replaceFragment(currentFragment, "ALARM_FRAGMENT");
                updateViewByState(state);
                break;
            case STATE_CLOCK:
                currentFragment = ClockFragment.newInstance("", "");
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

//    private void addDataDefaultToDB() {
//        Realm mRealm = Realm.getDefaultInstance();
//        DbUtil.addAlarmToDb(mRealm, "1:1", true);
//        DbUtil.addAlarmToDb(mRealm, "1:2", true);
//        DbUtil.addAlarmToDb(mRealm, "1:3", true);
//        DbUtil.addAlarmToDb(mRealm, "1:4", true);
//        DbUtil.addAlarmToDb(mRealm, "1:5", true);
//        DbUtil.addAlarmToDb(mRealm, "1:6", true);
//        DbUtil.addAlarmToDb(mRealm, "1:7", true);
//        DbUtil.addAlarmToDb(mRealm, "1:8", true);
//        DbUtil.addAlarmToDb(mRealm, "1:9", true);
//        DbUtil.addAlarmToDb(mRealm, "1:10", true);
//        DbUtil.addAlarmToDb(mRealm, "1:11", true);
//        DbUtil.addAlarmToDb(mRealm, "1:12", true);
//        DbUtil.addAlarmToDb(mRealm, "1:13", true);
//        DbUtil.addAlarmToDb(mRealm, "1:14", true);
//        DbUtil.addAlarmToDb(mRealm, "1:15", true);
//        DbUtil.addAlarmToDb(mRealm, "1:16", true);
//        DbUtil.addAlarmToDb(mRealm, "1:17", true);
//        DbUtil.addAlarmToDb(mRealm, "1:18", true);
//        DbUtil.addAlarmToDb(mRealm, "1:19", true);
//        DbUtil.addAlarmToDb(mRealm, "1:20", true);
//        DbUtil.addAlarmToDb(mRealm, "1:21", true);
//        DbUtil.addAlarmToDb(mRealm, "1:22", true);
//        DbUtil.addAlarmToDb(mRealm, "1:23", true);
//        DbUtil.addAlarmToDb(mRealm, "1:24", true);
//        DbUtil.addAlarmToDb(mRealm, "1:25", true);
//        DbUtil.addAlarmToDb(mRealm, "1:26", true);
//        DbUtil.addAlarmToDb(mRealm, "1:27", true);
//        DbUtil.addAlarmToDb(mRealm, "1:28", true);
//        DbUtil.addAlarmToDb(mRealm, "1:29", true);
//        DbUtil.addAlarmToDb(mRealm, "1:30", true);
//        DbUtil.addAlarmToDb(mRealm, "1:31", true);
//        DbUtil.addAlarmToDb(mRealm, "1:32", true);
//        DbUtil.addAlarmToDb(mRealm, "1:33", true);
//        DbUtil.addAlarmToDb(mRealm, "1:34", true);
//        DbUtil.addAlarmToDb(mRealm, "1:35", true);
//        DbUtil.addAlarmToDb(mRealm, "1:36", true);
//        DbUtil.addAlarmToDb(mRealm, "1:37", true);
//        DbUtil.addAlarmToDb(mRealm, "1:38", true);
//
//
//    }


}
