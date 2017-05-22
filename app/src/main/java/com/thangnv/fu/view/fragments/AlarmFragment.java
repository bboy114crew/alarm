package com.thangnv.fu.view.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.thangnv.fu.AlarmReceiver;
import com.thangnv.fu.view.dialogs.AlarmDialog;
import com.thangnv.fu.view.adapters.CustomListAdapter;
import com.thangnv.fu.R;
import com.thangnv.fu.base.BaseFragment;
import com.thangnv.fu.utils.DbUtil;
import com.thangnv.fu.listener.OnClickItemListViewListener;
import com.thangnv.fu.listener.OnSaveAlarmListener;
import com.thangnv.fu.model.AlarmInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

import static android.content.Context.ALARM_SERVICE;


public class AlarmFragment extends BaseFragment implements OnSaveAlarmListener {

    private static final String TAG = "AlarmFragment";
    private static final String PREFS_TAG = "SharedPrefs";
    private static final String ALARMS_TAG = "MyAlarm";


    private CustomListAdapter adapter;
    //private List<Long> listAlarms;
    private Realm realm;
    private List<AlarmInfo> mAlarmInfos;
    //private ImageView addAlarm;
    //private TextView deleteAlarm;


    public AlarmFragment() {
    }

    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();
        mAlarmInfos = DbUtil.getInstance().getAllAlarm(realm);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new CustomListAdapter(getActivity(), mAlarmInfos, new OnClickItemListViewListener() {
            @Override
            public void OnClickItem(View mView, int position) {
                AlarmDialog alarmDialog = new AlarmDialog(getActivity(), mAlarmInfos.get(position).getTimeAlarm(),
                        position, AlarmFragment.this);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alarmDialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                alarmDialog.show();
                alarmDialog.getWindow().setAttributes(lp);
            }

            @Override
            public void onUpdateStatus(View mView, boolean status, long id, int position) {
                AlarmInfo mAlarmInfo = DbUtil.getInstance().updateAlarmStatus(realm, status, id);
                mAlarmInfos.set(position, mAlarmInfo);
                adapter.notifyDataSetChanged();
                if(status == false){

                }
            }



        });

        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listAlarm);
        listView.setAdapter(adapter);


        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Log.d(TAG, "onChange: " + realm);
            }
        });
        return view;
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }


    @Override
    public void onCancel() {

    }

    @Override
    public void onSaveSuccess(String time, int position, int state) {
        if (state == OnSaveAlarmListener.STATE_EDIT) {
            AlarmInfo mAlarmInfo = DbUtil.getInstance().updateAlarmTime(realm, time, mAlarmInfos.get(position).getId());
            mAlarmInfos.set(position, mAlarmInfo);
            adapter.notifyDataSetChanged();
        } else {
            AlarmInfo mAlarmInfo =new AlarmInfo();
            mAlarmInfo.setTimeAlarm(time);
            mAlarmInfo.setStateAlarm(true);
            long maxId = 1;
            if(realm.isEmpty()){
                Log.d(TAG, "onSaveSuccess: isEmpty");
            }else{
                Log.d(TAG, "onSaveSuccess: not Empty");
                 maxId = (long) realm.where(AlarmInfo.class).max("id")+1;
            }
            mAlarmInfo.setId(maxId);
            mAlarmInfos.add(mAlarmInfo);
            adapter.notifyDataSetChanged();
            DbUtil.getInstance().addAlarmToDb(realm, time, true);
        }
    }





}
