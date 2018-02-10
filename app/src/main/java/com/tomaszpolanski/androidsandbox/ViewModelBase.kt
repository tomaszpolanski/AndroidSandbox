package com.tomaszpolanski.androidsandbox

import android.arch.lifecycle.ViewModel
import android.os.Parcelable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor

abstract class ViewModelBase<S : Parcelable, in E>(
    private val schedulerProvider: SchedulerProvider
                                                  ) : ViewModel() {

    private val intentStream = PublishProcessor.create<Pair<E, S?>>()

    fun getState(restored: S?, initial: S): Flowable<S> = intentStream
        .flatMapSingle { (event, previous) ->
            reduce(previous ?: restored ?: initial, event)
        }
        .observeOn(schedulerProvider.ui())

    fun intent(event: E, previousState: S?) =
        intentStream.onNext(event to previousState)

    abstract fun reduce(previous: S, event: E): Single<S>
}
