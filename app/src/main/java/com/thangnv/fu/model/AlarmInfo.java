package com.thangnv.fu.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ThangNV28 on 5/15/2017.
 */

public class AlarmInfo extends RealmObject {
    @PrimaryKey
    private long id;
    private String timeAlarm;
    private boolean stateAlarm;
    //private int alarmType;
//    private String labelAlarm;
//    private String soundAlarm;
//    private boolean stateSnoodze;
    private RealmList<RealmInteger> dayRepeate;

    public AlarmInfo() {
        dayRepeate = new RealmList<>();
        for (int i = 0; i < 7; i++) {
            RealmInteger realmInteger = new RealmInteger();
            realmInteger.setValue(0);
            dayRepeate.add(realmInteger);
        }
    }

    public RealmList<RealmInteger> getDayRepeate() {
        return dayRepeate;
    }

    public void setDayRepeate(RealmList<RealmInteger> dayRepeate) {
        this.dayRepeate = dayRepeate;
    }
    //    public boolean isStateSnoodze() {
//        return stateSnoodze;
//    }
//
//    public void setStateSnoodze(boolean stateSnoodze) {
//        this.stateSnoodze = stateSnoodze;
//    }
//
//    public String getSoundAlarm() {
//
//        return soundAlarm;
//    }
//
//    public void setSoundAlarm(String soundAlarm) {
//        this.soundAlarm = soundAlarm;
//    }
//
//    public String getLabelAlarm() {
//        return labelAlarm;
//    }
//
//    public void setLabelAlarm(String labelAlarm) {
//        this.labelAlarm = labelAlarm;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeAlarm() {
        return timeAlarm;
    }

    public void setTimeAlarm(String timeAlarm) {
        this.timeAlarm = timeAlarm;
    }

    public boolean isStateAlarm() {
        return stateAlarm;
    }

    public void setStateAlarm(boolean stateAlarm) {
        this.stateAlarm = stateAlarm;
    }

}
