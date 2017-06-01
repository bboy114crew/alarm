package com.thangnv.fu.model;

import io.realm.RealmObject;

/**
 * Created by ll on 5/29/2017.
 */

public class RealmInteger extends RealmObject {
    private int value;

    public RealmInteger() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
