package com.tomaszpolanski.androidsandbox


import android.content.Context

import com.tomaszpolanski.androidsandbox.inject.app.BaseApplicationComponent
import com.tomaszpolanski.androidsandbox.inject.app.ForApplication
import com.tomaszpolanski.androidsandbox.persistance.AppDatabase

import javax.inject.Singleton

import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
interface ApplicationComponent : BaseApplicationComponent {

    override fun getApplication(): android.app.Application

    @ForApplication
    override fun getApplicationContext(): Context

    fun provideDatabase(): AppDatabase

    fun inject(application: App)
}
