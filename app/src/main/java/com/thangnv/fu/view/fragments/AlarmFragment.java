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
import com.thangnv.fu.model.RealmInteger;
import com.thangnv.fu.utils.DbUtil;
import com.thangnv.fu.utils.LogUtil;
import com.thangnv.fu.view.adapters.CustomListAlarmAdapter;
import com.thangnv.fu.view.dialogs.AlarmDialog;

import java.util.ArrayList;
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
    private AlarmInfo alarmInfo;

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
                alarmInfo = mAlarmInfos.get(position);
                AlarmDialog alarmDialog = new AlarmDialog(getActivity(),alarmInfo,
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
    public void onSaveSuccess(AlarmInfo alarmInfo, int position, int state) {
        if (state == STATE_EDIT) {
            AlarmInfo mAlarmInfo = DbUtil.getInstance().updateAlarm(realm, alarmInfo, mAlarmInfos.get(position).getId());
            mAlarmInfos.set(position, mAlarmInfo);
            adapter.notifyDataSetChanged();
            LogUtil.d(TAG, "Update alarm");
        } else {
            AlarmInfo mAlarmInfo = alarmInfo;
            mAlarmInfo.setTimeAlarm(alarmInfo.getTimeAlarm());
            mAlarmInfo.setStateAlarm(true);
            long maxId = 1;
            if (realm.isEmpty()) {
                LogUtil.d(TAG, "onSaveSuccess: isEmpty");
            } else {
                LogUtil.d(TAG, "onSaveSuccess: not Empty");
                maxId = (long) realm.where(AlarmInfo.class).max("id") + 1;
            }
            mAlarmInfo.setId(maxId);
            List<RealmInteger> realmIntegers = new ArrayList<>();
            for(int i=0;i<alarmInfo.getDayRepeate().size();i++){
                realmIntegers.add(alarmInfo.getDayRepeate().get(i));
            }
            mAlarmInfos.add(mAlarmInfo);
            adapter.notifyDataSetChanged();
            DbUtil.getInstance().addAlarmToDb(realm, alarmInfo.getTimeAlarm(), true, realmIntegers);
            LogUtil.d(TAG, "Add data to database");
        }
    }

    @Override
    public void onDelete() {

    }
}
