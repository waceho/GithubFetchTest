package com.moneway.test.di.module;

import com.moneway.test.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public interface ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    MainActivity contributeMainActivity();

}
