package com.moneway.test.di.module;

import com.moneway.test.ui.detail.DetailsFragment;
import com.moneway.test.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentModule {
    @ContributesAndroidInjector
    HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    DetailsFragment contributeDetailsFragment();
}
