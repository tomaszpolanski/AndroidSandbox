package com.tomaszpolanski.androidsandbox.main

import com.tomaszpolanski.androidsandbox.ApplicationComponent
import com.tomaszpolanski.androidsandbox.inject.activity.ActivityScope
import com.tomaszpolanski.androidsandbox.inject.activity.BaseActivityComponent

import dagger.Component


@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainActivityModule::class))
internal interface MainActivityComponent : BaseActivityComponent {

    val viewModel: MainViewModel

    fun inject(activity: MainActivity)
}
