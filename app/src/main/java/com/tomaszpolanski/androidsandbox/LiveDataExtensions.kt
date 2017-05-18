package com.tomaszpolanski.androidsandbox

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.Observer
import org.reactivestreams.Publisher

fun <T : Any> Publisher<T>.observe(owner: LifecycleOwner, onChanged: (T) -> Unit) {
    LiveDataReactiveStreams.fromPublisher(this).observe(owner, Observer { onChanged(it!!) })
}

