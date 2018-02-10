package com.tomaszpolanski.androidsandbox

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity

abstract class ActivityIntent<S : Parcelable, E> : AppCompatActivity() {

    private val stateKey = javaClass.name
    private var lastState: S? = null
    protected lateinit var viewModel: ViewModelBase<S, E>

    protected open fun updateState(state: S) {
        lastState = state
    }

    protected fun bind(savedInstanceState: Bundle?, initial: S) {
        viewModel.getState(readLastState(savedInstanceState), initial)
            .observe(this, this::updateState)
        onBind()
    }

    protected abstract fun onBind()

    fun intent(event: E) = viewModel.intent(event, lastState)

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(stateKey, lastState)
    }

    private fun readLastState(savedInstanceState: Bundle?): S? =
        savedInstanceState?.getParcelable(stateKey) as? S
}
