package com.tomaszpolanski.androidsandbox.main

import com.tomaszpolanski.androidsandbox.ApplicationComponent
import com.tomaszpolanski.androidsandbox.inject.activity.ActivityScope
import com.tomaszpolanski.androidsandbox.inject.activity.BaseActivityComponent
import com.tomaszpolanski.androidsandbox.persistance.AppDatabase

import dagger.Component


@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent : BaseActivityComponent {

    val viewModel: MainViewModel

    fun inject(activity: MainActivity)
}
