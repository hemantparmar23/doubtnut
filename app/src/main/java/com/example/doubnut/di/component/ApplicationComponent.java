package com.example.doubnut.di.component;

import android.app.Application;

import com.example.doubnut.base.BaseApplication;
import com.example.doubnut.di.module.ActivityBindingModule;
import com.example.doubnut.di.module.ApplicationModule;
import com.example.doubnut.di.module.ContextModule;
import com.example.doubnut.di.module.DoubnutModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class,
        ActivityBindingModule.class, DoubnutModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}