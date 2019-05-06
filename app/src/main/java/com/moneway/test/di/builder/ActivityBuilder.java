package com.moneway.test.di.builder;

import com.moneway.test.ui.main.MainActivity;
import com.moneway.test.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainModule.class})
    MainActivity bindMainActivity();
}
