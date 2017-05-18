
package com.tomaszpolanski.androidsandbox.core


import android.app.Application
import android.support.annotation.CallSuper

import com.tomaszpolanski.androidsandbox.inject.Injector

abstract class BaseApplication<T> : Application(), Injector<T> {

    private var component: T? = null

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        inject()
    }

    override fun component(): T {
        val oldComponent = component

        if (oldComponent == null) {
            val newComponent = createComponent()
            component = newComponent
            return newComponent
        } else {
            return oldComponent
        }
    }

    protected abstract fun createComponent(): T

}
