package com.moneway.test.di.module;

import com.moneway.test.FactoryViewModel;
import com.moneway.test.di.ViewModelKey;
import com.moneway.test.ui.detail.DetailsViewModel;
import com.moneway.test.ui.home.HomeViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
