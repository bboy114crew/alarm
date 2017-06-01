package com.thangnv.fu.utils;

import android.util.Log;

import com.thangnv.fu.model.AlarmInfo;
import com.thangnv.fu.model.RealmInteger;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by ll on 5/18/2017.
 */

public class DbUtil {
    private static DbUtil dbUtil;

    public static DbUtil getInstance() {
        if (dbUtil == null) {
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }

    public static void addAlarmToDb(Realm realm, final String time, final boolean status, final List<RealmInteger> dayRepeate) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number maxId = realm.where(AlarmInfo.class).max("id");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                AlarmInfo alarmInfo = realm.createObject(AlarmInfo.class, nextId);
                alarmInfo.setTimeAlarm(time);
                alarmInfo.setStateAlarm(status);
                RealmList<RealmInteger> realmIntegers = new RealmList<>();

                for (int i = 0; i < dayRepeate.size(); i++) {
                    RealmInteger realmInteger = realm.createObject(RealmInteger.class);
                    realmInteger.setValue(dayRepeate.get(i).getValue());
                    realmIntegers.add(realmInteger);
                }
                alarmInfo.setDayRepeate(realmIntegers);
                for (int i = 0; i < realmIntegers.size(); i++) {
                    Log.d("Day ", "" + realmIntegers.get(i).getValue());
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                LogUtil.d("Add ", "Success");

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                LogUtil.d("Add ", "Failed");
            }
        });
    }


    public static List<AlarmInfo> getAllAlarm(Realm realm) {
        List<AlarmInfo> alarmInfoList = new RealmList<>();
        RealmResults<AlarmInfo> realmResults = realm.where(AlarmInfo.class).findAll();
        alarmInfoList.addAll(realmResults);
        return alarmInfoList;
    }


    public static AlarmInfo updateAlarm(Realm realm, AlarmInfo mAlarmInfo, long id) {
        AlarmInfo alarmInfo = realm.where(AlarmInfo.class).equalTo("id", id).findFirst();
        realm.beginTransaction();
        alarmInfo.setTimeAlarm(mAlarmInfo.getTimeAlarm());
        alarmInfo.setDayRepeate(mAlarmInfo.getDayRepeate());

        realm.commitTransaction();
        return alarmInfo;
    }


    public static AlarmInfo updateAlarmStatus(Realm realm, boolean state, long id) {
        AlarmInfo alarmInfo = realm.where(AlarmInfo.class).equalTo("id", id).findFirst();
        realm.beginTransaction();
        alarmInfo.setStateAlarm(state);
        realm.commitTransaction();
        return alarmInfo;
    }


}
