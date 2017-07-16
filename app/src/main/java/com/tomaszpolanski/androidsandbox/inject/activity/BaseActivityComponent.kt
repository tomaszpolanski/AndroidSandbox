package com.tomaszpolanski.androidsandbox.inject.activity

import com.tomaszpolanski.androidsandbox.ApplicationComponent
import com.tomaszpolanski.androidsandbox.inject.activity.ActivityScope

import android.content.Context

import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(BaseActivityModule::class))
interface BaseActivityComponent {

    val activity: android.app.Activity

    @get:ForActivity
    val activityContext: Context
}
