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
import com.thangnv.fu.utils.LogUtil;
import com.thangnv.fu.view.dialogs.AlarmDialog;
import com.thangnv.fu.view.fragments.AlarmFragment;
import com.thangnv.fu.view.fragments.ClockFragment;

import static com.thangnv.fu.common.Constants.STATE_ALARM;
import static com.thangnv.fu.common.Constants.STATE_CLOCK;
import static com.thangnv.fu.common.Constants.STATE_TIMER;
import static com.thangnv.fu.common.Constants.STATE_WATCH;

public class MainActivity extends BaseActivity implements View.OnClickListener, ClockFragment.OnFragmentInteractionListener {

    private TextView tvTitle;


    private ImageView btnEdit;
    private ImageView btnAdd;
    private LinearLayout viewAlarm;
    private LinearLayout viewWatch;
    private LinearLayout viewTimer;
    private LinearLayout viewStopWatch;
    private ImageView btnAlarm;
    private TextView txtAlarm;
    private ImageView btnClock;
    private TextView txtClock;
    private ImageView btnTimer;
    private TextView txtTimer;
    private ImageView btnStopWatch;
    private TextView txtStopWatch;
    private TextView txtDeleteAlarm;
    private Fragment currentFragment;
    private int stateFragment ;

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewAlarm.setOnClickListener(this);
        viewWatch.setOnClickListener(this);
        viewTimer.setOnClickListener(this);
        viewStopWatch.setOnClickListener(this);
        setStateFragment(STATE_CLOCK);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAddBySate(stateFragment);
            }
        });
        changeFragmentByState(stateFragment);
    }

    public void setStateFragment(int stateFragment) {
        this.stateFragment = stateFragment;
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
                setStateFragment(STATE_CLOCK);
                changeFragmentByState(stateFragment);
                break;
            case R.id.view_alarm:
                Log.d(TAG, "onClick: view_alarm");
                setStateFragment(STATE_ALARM);
                changeFragmentByState(stateFragment);
                break;
            case R.id.view_timer:
                LogUtil.d(TAG, "onClick: view_timer");
                setStateFragment(STATE_TIMER);
                updateViewByState(stateFragment);
                break;
            case R.id.view_stop_watch:
                LogUtil.d(TAG, "onClick: view_stop_watch");
                setStateFragment(STATE_WATCH);
                updateViewByState(stateFragment);
                break;
            case R.id.btn_edit:
                clickEditByState(stateFragment);

        }
    }

    @Override
    public void initViews() {
        super.initViews();
        viewAlarm = (LinearLayout) findViewById(R.id.view_alarm);
        viewWatch = (LinearLayout) findViewById(R.id.view_watch);
        viewTimer = (LinearLayout) findViewById(R.id.view_timer);
        viewStopWatch = (LinearLayout) findViewById(R.id.view_stop_watch);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnEdit = (ImageView) findViewById(R.id.btn_edit);
        btnAdd = (ImageView) findViewById(R.id.btn_add);
        btnAlarm = (ImageView) findViewById(R.id.btn_alarm);
        txtAlarm = (TextView) findViewById(R.id.txt_alarm);
        btnClock = (ImageView) findViewById(R.id.btn_clock);
        txtClock = (TextView) findViewById(R.id.txt_clock);
        btnTimer = (ImageView) findViewById(R.id.btn_timer);
        txtTimer = (TextView) findViewById(R.id.txt_timer);
        btnStopWatch = (ImageView) findViewById(R.id.btn_stop_watch);
        txtStopWatch = (TextView) findViewById(R.id.txt_stop_watch);
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment, tag);
        fragmentTransaction.commit();
    }

    private void changeFragmentByState(int state) {
        switch (state) {
            case STATE_ALARM:
                setCurrentFragment(AlarmFragment.newInstance());
                replaceFragment(currentFragment, "ALARM_FRAGMENT");
                updateViewByState(state);
                break;
            case STATE_CLOCK:
                setCurrentFragment(ClockFragment.newInstance());
                replaceFragment(currentFragment, "CLOCK_FRAGMENT");
                updateViewByState(state);
                break;
        }
    }

    private void updateViewByState(int state) {
        switch (state) {
            case STATE_CLOCK:
                tvTitle.setText(getString(R.string.world_clock));
                btnClock.setImageResource(R.drawable.ic_lens_selected);
                btnAlarm.setImageResource(R.drawable.ic_alarm_selector);
                btnTimer.setImageResource(R.drawable.ic_timer_selector);
                btnStopWatch.setImageResource(R.drawable.ic_watch_selector);
                txtClock.setTextColor(ContextCompat.getColor(this, R.color.selected_color));
                txtAlarm.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtTimer.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtStopWatch.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                break;
            case STATE_ALARM:
                tvTitle.setText(getString(R.string.alarm));
                btnAlarm.setImageResource(R.drawable.ic_alarm_selected);
                btnClock.setImageResource(R.drawable.ic_lens_selector);
                btnTimer.setImageResource(R.drawable.ic_timer_selector);
                btnStopWatch.setImageResource(R.drawable.ic_watch_selector);
                txtClock.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtAlarm.setTextColor(ContextCompat.getColor(this, R.color.selected_color));
                txtTimer.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtStopWatch.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                break;
            case STATE_TIMER:
                btnAlarm.setImageResource(R.drawable.ic_alarm_selector);
                btnClock.setImageResource(R.drawable.ic_lens_selector);
                btnTimer.setImageResource(R.drawable.ic_timer_selected);
                btnStopWatch.setImageResource(R.drawable.ic_watch_selector);
                txtClock.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtAlarm.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtTimer.setTextColor(ContextCompat.getColor(this, R.color.selected_color));
                txtStopWatch.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                break;
            case STATE_WATCH:
                btnAlarm.setImageResource(R.drawable.ic_alarm_selector);
                btnClock.setImageResource(R.drawable.ic_lens_selector);
                btnTimer.setImageResource(R.drawable.ic_timer_selector);
                btnStopWatch.setImageResource(R.drawable.ic_watch_selected);
                txtClock.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtAlarm.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtTimer.setTextColor(ContextCompat.getColor(this, R.color.tab_text_selector));
                txtStopWatch.setTextColor(ContextCompat.getColor(this, R.color.selected_color));
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
                LogUtil.d(TAG, "Click add clock button");
                break;
            case STATE_ALARM:
                LogUtil.d(TAG, "Click add alarm button");
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

                    @Override
                    public void onDelete() {

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
