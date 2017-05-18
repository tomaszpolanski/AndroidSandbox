package com.tomaszpolanski.androidsandbox.core


import android.os.Bundle
import android.support.annotation.CallSuper

import com.tomaszpolanski.androidsandbox.viewmodel.BaseLifecycleViewDataBinder
import com.tomaszpolanski.androidsandbox.viewmodel.DataBinder
import com.tomaszpolanski.androidsandbox.viewmodel.ViewModel

import io.reactivex.disposables.CompositeDisposable

abstract class BindingBaseActivity<T> : BaseActivity<T>() {

    private val lifecycleBinder = object : BaseLifecycleViewDataBinder() {

        override fun bind(disposables: CompositeDisposable) {
            dataBinder().bind(disposables)
        }

        override fun unbind() {
            dataBinder().unbind()
        }

        override fun viewModel(): ViewModel {
            return this@BindingBaseActivity.viewModel()
        }

    }

    @CallSuper
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleBinder.onCreate()
    }

    @CallSuper
    public override fun onResume() {
        super.onResume()
        lifecycleBinder.onResume()
    }

    @CallSuper
    public override fun onPause() {
        lifecycleBinder.onPause()
        super.onPause()
    }

    @CallSuper
    public override fun onDestroy() {
        lifecycleBinder.onDestroyView()
        lifecycleBinder.onDestroy()
        super.onDestroy()
    }

    protected abstract fun viewModel(): ViewModel

    protected abstract fun dataBinder(): DataBinder
}
