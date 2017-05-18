package com.tomaszpolanski.androidsandbox

import io.reactivex.Scheduler

interface SchedulerProvider {

    val isUiThread: Boolean

    fun computation(): Scheduler

    fun trampoline(): Scheduler

    fun single(): Scheduler

    fun newThread(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun assertUiThread()
}
