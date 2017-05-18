package com.tomaszpolanski.androidsandbox.core


import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity

import com.tomaszpolanski.androidsandbox.inject.Injector

abstract class BaseActivity<T> : AppCompatActivity(), Injector<T> {

    private var component: T? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun component(): T {
        val comp = component
        if (comp == null) {
            val newComponent = createComponent()
            component = newComponent
            return newComponent
        } else {
            return comp
        }
    }

    protected abstract fun createComponent(): T
}
