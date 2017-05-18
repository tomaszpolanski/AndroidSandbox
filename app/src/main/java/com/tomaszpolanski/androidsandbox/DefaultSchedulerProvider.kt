package com.tomaszpolanski.androidsandbox

import android.os.Looper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DefaultSchedulerProvider : SchedulerProvider {

    override val isUiThread: Boolean
        get() = Looper.getMainLooper().thread == Thread.currentThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun trampoline(): Scheduler = Schedulers.trampoline()

    override fun single(): Scheduler = Schedulers.single()

    override fun newThread(): Scheduler = Schedulers.newThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun assertUiThread() {
        if (!isUiThread) {
            throw IllegalStateException(
                "This task must be run on the Main thread and not on a worker thread.")
        }
    }
}
