package com.thangnv.fu.model;

/**
 * Created by ThangNV28 on 5/15/2017.
 */

public class Alarms {
    private String timeAlarm;
    private boolean stateAlarm;

    public Alarms(String timeAlarm, boolean stateAlarm) {
        this.timeAlarm = timeAlarm;
        this.stateAlarm = stateAlarm;
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
