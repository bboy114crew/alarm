package com.thangnv.fu.common;

import com.thangnv.fu.model.AlarmInfo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by ll on 5/18/2017.
 */

public class DbUtil {
    public static void addAlarmToDb(Realm mRealm, final String time, final boolean status) {

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Get the current max id in the users table
                Number maxId = realm.where(AlarmInfo.class).max("id");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                AlarmInfo mAlarmInfo = realm.createObject(AlarmInfo.class, nextId);
                mAlarmInfo.setTimeAlarm(time);
                mAlarmInfo.setStateAlarm(status);
            }
        });
    }

    public static List<AlarmInfo> getAllAlarm(Realm realm) {
        List<AlarmInfo> mAlarmInfos = new RealmList<>();
        RealmResults<AlarmInfo> realmResults = realm.where(AlarmInfo.class).findAll();
        mAlarmInfos.addAll(realmResults);
        return mAlarmInfos;
    }

    public static AlarmInfo updateAlarmStatus(Realm realm, long id, boolean status) {
        AlarmInfo alarmInfo = realm.where(AlarmInfo.class).equalTo("id", id).findFirst();
        realm.beginTransaction();
        alarmInfo.setStateAlarm(status);
        realm.commitTransaction();
        return alarmInfo;
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
