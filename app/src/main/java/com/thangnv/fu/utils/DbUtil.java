package com.thangnv.fu.utils;

import com.thangnv.fu.model.AlarmInfo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by ll on 5/18/2017.
 */

public class DbUtil {
    private static DbUtil dbUtil;

    public static DbUtil getInstance(){
        if (dbUtil == null){
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }
    public static void addAlarmToDb(Realm realm, final String time, final boolean status) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number maxId = realm.where(AlarmInfo.class).max("id");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                AlarmInfo alarmInfo = realm.createObject(AlarmInfo.class, nextId);
                alarmInfo.setTimeAlarm(time);
                alarmInfo.setStateAlarm(status);

            }
        });
    }


    public static List<AlarmInfo> getAllAlarm(Realm realm) {
        List<AlarmInfo> alarmInfoList = new RealmList<>();
        RealmResults<AlarmInfo> realmResults = realm.where(AlarmInfo.class).findAll();
        alarmInfoList.addAll(realmResults);
        return alarmInfoList;
    }



    public static AlarmInfo updateAlarmTime(Realm realm, String time, long id) {
        AlarmInfo alarmInfo = realm.where(AlarmInfo.class).equalTo("id", id).findFirst();
        realm.beginTransaction();
        alarmInfo.setTimeAlarm(time);
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
