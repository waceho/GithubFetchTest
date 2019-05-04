package com.moneway.test.ui.main;

import com.moneway.test.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainModule {

    @ContributesAndroidInjector
    HomeFragment provideHomeFragment();

}
