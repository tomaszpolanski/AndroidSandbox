package com.tomaszpolanski.androidsandbox.main

import com.tomaszpolanski.androidsandbox.inject.activity.ActivityScope
import com.tomaszpolanski.androidsandbox.inject.activity.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(BaseActivityModule::class))
internal class MainActivityModule {

    @Provides
    @ActivityScope
    fun getViewModel(): MainViewModel =
        MainViewModel()

}
