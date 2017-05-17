package com.thangnv.fu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ClockFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";

    private static final int STATE_ALARM = 1;
    private static final int STATE_CLOCK = 2;
    private static final int STATE_WATCH = 3;
    private static final int STATE_TIMER = 4;

    private TextView tvTitle;
    private TextView btnEdit;
    private ImageView btnAdd;
    private LinearLayout viewAlarm;

    private int stateFragment=STATE_CLOCK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAlarm = (LinearLayout) findViewById(R.id.view_alarm);
        viewAlarm.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnEdit = (TextView) findViewById(R.id.btn_edit);
        btnAdd = (ImageView) findViewById(R.id.btn_add);
        findViewById(R.id.view_watch).setOnClickListener(this);
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
                stateFragment=STATE_ALARM;
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

    private void changeFragmentByState(int state){
        switch (state){
            case STATE_ALARM:
                AlarmFragment alarmFragment = AlarmFragment.newInstance("","");
                replaceFragment(alarmFragment, "ALARM_FRAGMENT");
                updateViewByState(state);
                break;
            case STATE_CLOCK:
                ClockFragment clockFragment = ClockFragment.newInstance("", "");
                replaceFragment(clockFragment, "CLOCK_FRAGMENT");
                updateViewByState(state);
                break;
        }
    }
    private void updateViewByState(int state){
        switch (state) {
            case STATE_CLOCK:
                tvTitle.setText("World Clock");
                break;
            case STATE_ALARM:
                tvTitle.setText("Alarm");
                break;
        }
    }

    private void clickEditByState(int state){
        switch (state) {
            case STATE_CLOCK:

                break;
            case STATE_ALARM:

                break;
        }
    }

}
