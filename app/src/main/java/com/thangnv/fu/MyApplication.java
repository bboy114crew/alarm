package com.thangnv.fu;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by ll on 5/18/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
    }
}

