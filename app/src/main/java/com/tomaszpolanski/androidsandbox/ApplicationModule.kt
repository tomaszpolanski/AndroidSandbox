package com.tomaszpolanski.androidsandbox


import com.tomaszpolanski.androidsandbox.inject.app.BaseApplicationModule
import com.tomaszpolanski.androidsandbox.network.NetworkModule

import dagger.Module

@Module(includes = arrayOf(BaseApplicationModule::class, NetworkModule::class))
internal class ApplicationModule
