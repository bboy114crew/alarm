package com.thangnv.fu.model;

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
//    private String labelAlarm;
//    private String soundAlarm;
//    private boolean stateSnoodze;
//    public RealmList<Integer> dayRepeate;

    public AlarmInfo() {
    }
//    public ArrayList<Integer> getDayRepeate() {
//        return dayRepeate;
//    }
//
//    public void setDayRe peate(ArrayList<Integer> dayRepeate) {
//        this.dayRepeate = dayRepeate;
//    }

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
