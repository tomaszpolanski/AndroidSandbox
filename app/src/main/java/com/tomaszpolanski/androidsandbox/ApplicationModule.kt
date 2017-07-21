package com.tomaszpolanski.androidsandbox


import com.tomaszpolanski.androidsandbox.inject.app.BaseApplicationModule
import com.tomaszpolanski.androidsandbox.network.NetworkModule
import com.tomaszpolanski.androidsandbox.persistance.PersistenceModule

import dagger.Module

@Module(includes = arrayOf(BaseApplicationModule::class,
        NetworkModule::class,
        PersistenceModule::class))
internal class ApplicationModule
