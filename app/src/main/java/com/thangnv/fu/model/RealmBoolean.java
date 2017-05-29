package com.thangnv.fu.model;

import io.realm.RealmObject;

/**
 * Created by ll on 5/26/2017.
 */

public class RealmBoolean extends RealmObject{
    private boolean value;

    public RealmBoolean() {

    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
