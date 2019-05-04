package com.moneway.test;

import android.app.Activity;
import android.app.Application;

import com.moneway.test.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

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

    }

    private void initDagger() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }


}
