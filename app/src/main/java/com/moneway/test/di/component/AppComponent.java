package com.moneway.test.di.component;

import android.app.Application;

import com.moneway.test.MonewayTestApp;
import com.moneway.test.di.builder.ActivityBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ActivityBuilder.class})
public interface AppComponent {
    // inject App
    void inject(MonewayTestApp monewayTestApp);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
