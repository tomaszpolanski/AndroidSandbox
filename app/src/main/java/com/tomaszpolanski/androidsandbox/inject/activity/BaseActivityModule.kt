package com.tomaszpolanski.androidsandbox.inject.activity


import android.content.Context

import dagger.Module
import dagger.Provides

@Module
internal class BaseActivityModule(private val activity: android.app.Activity) {

    @Provides
    @ActivityScope
    @ForActivity
    fun provideActivityContext(): Context =
        activity

    @Provides
    @ActivityScope
    internal fun provideActivity(): android.app.Activity =
        activity
}
