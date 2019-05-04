package com.moneway.test.di.builder;

import com.moneway.test.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuilder {
    @ContributesAndroidInjector(modules = {
            // TODO add future fragment module here
            //RateUsDialogProvider.class
    })
    MainActivity bindMainActivity();
}
