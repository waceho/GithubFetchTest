package com.moneway.test;

import android.app.Activity;
import android.app.Application;

import com.moneway.test.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MonewayTestApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // build dagger
        initDagger();
        // init realm
        initRealm();
    }

    private void initDagger() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    private void initRealm(){
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("githum.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }


}
