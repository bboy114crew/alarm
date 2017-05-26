package com.thangnv.fu.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.thangnv.fu.R;
import com.thangnv.fu.base.BaseFragment;
import com.thangnv.fu.listener.OnClickItemListViewListener;
import com.thangnv.fu.listener.OnSaveAlarmListener;
import com.thangnv.fu.model.AlarmInfo;
import com.thangnv.fu.utils.DbUtil;
import com.thangnv.fu.utils.LogUtil;
import com.thangnv.fu.view.adapters.CustomListAlarmAdapter;
import com.thangnv.fu.view.dialogs.AlarmDialog;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

import static com.thangnv.fu.common.Constants.STATE_EDIT;


public class AlarmFragment extends BaseFragment implements OnSaveAlarmListener {


    private CustomListAlarmAdapter adapter;
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
        adapter = new CustomListAlarmAdapter(getActivity(), mAlarmInfos, new OnClickItemListViewListener() {
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
}


    @Override
    public void onCancel() {
        LogUtil.d(TAG, "onCancel");
    }

    @Override
    public void onSaveSuccess(String time, int position, int state) {
        if (state == STATE_EDIT) {
            AlarmInfo mAlarmInfo = DbUtil.getInstance().updateAlarmTime(realm, time, mAlarmInfos.get(position).getId());
            mAlarmInfos.set(position, mAlarmInfo);
            adapter.notifyDataSetChanged();
            LogUtil.d(TAG, "Update alarm");
        } else {
            AlarmInfo mAlarmInfo = new AlarmInfo();
            mAlarmInfo.setTimeAlarm(time);
            mAlarmInfo.setStateAlarm(true);
            long maxId = 1;
            if(realm.isEmpty()){
                LogUtil.d(TAG, "onSaveSuccess: isEmpty");
            }else{
                LogUtil.d(TAG, "onSaveSuccess: not Empty");
                maxId = (long) realm.where(AlarmInfo.class).max("id")+1;
            }
            mAlarmInfo.setId(maxId);
            mAlarmInfos.add(mAlarmInfo);
            adapter.notifyDataSetChanged();
            DbUtil.getInstance().addAlarmToDb(realm, time, true);
            LogUtil.d(TAG, "Add data to database");
        }
    }

    @Override
    public void onDelete() {

    }
}
