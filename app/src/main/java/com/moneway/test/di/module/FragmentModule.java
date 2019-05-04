package com.moneway.test.di.module;

import com.moneway.test.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}
