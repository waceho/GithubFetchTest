package com.moneway.test.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public interface ContextModule {

    @Binds
    Context provideContext(Application application);
}
