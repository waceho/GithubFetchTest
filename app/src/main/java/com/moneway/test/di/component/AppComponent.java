package com.moneway.test.di.component;

import android.app.Application;

import com.moneway.test.MonewayTestApp;
import com.moneway.test.di.module.ActivityBuilderModule;
import com.moneway.test.di.module.AppModule;
import com.moneway.test.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules={AndroidSupportInjectionModule.class, ActivityBuilderModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(MonewayTestApp app);
}
