package com.tomaszpolanski.androidsandbox;


import android.support.annotation.NonNull;

import com.tomaszpolanski.androidsandbox.core.BaseApplication;
import com.tomaszpolanski.androidsandbox.inject.app.BaseApplicationModule;
import com.tomaszpolanski.androidsandbox.network.NetworkModule;

public class App extends BaseApplication<ApplicationComponent> {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override

    public void inject() {
        component().inject(this);
    }


    @Override
    @NonNull
    protected ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .baseApplicationModule(new BaseApplicationModule(this))
                .networkModule(new NetworkModule("http://api"))
                .build();

    }
}