package com.tomaszpolanski.androidsandbox

import android.arch.lifecycle.ViewModel
import android.os.Parcelable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor

abstract class ViewModelBase<S : Parcelable, in E>(
    private val schedulerProvider: SchedulerProvider
                                                  ) : ViewModel() {

    private val intentPublishSubject = PublishProcessor.create<Pair<E, S?>>()

    fun getState(restored: S?, initial: S): Flowable<S> = intentPublishSubject
        .flatMapSingle { (event, previous) ->
            reduce(previous ?: restored ?: initial, event)
        }
        .observeOn(schedulerProvider.ui())

    fun intent(event: E, previousState: S?) =
        intentPublishSubject.onNext(event to previousState)

    abstract fun reduce(previous: S, intent: E): Single<S>
}
