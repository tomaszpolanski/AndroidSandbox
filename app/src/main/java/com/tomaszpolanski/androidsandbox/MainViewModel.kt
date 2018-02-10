package com.tomaszpolanski.androidsandbox

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize


private const val MAX_CLICK_COUNT = 10

sealed class Event {
    object LongClick : Event()
    object ShortClick : Event()
}

@SuppressLint("ParcelCreator")
sealed class State : Parcelable {
    abstract val totalClicks: Int

    @Parcelize data class HiddenToast(override val totalClicks: Int) : State()
    @Parcelize data class ShownToast(override val totalClicks: Int) : State() {
        val text get() = "You are ${MAX_CLICK_COUNT - totalClicks} click(s) away to be Groupon dev"
    }
}

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    private val intentLauncher: Router
                   ) : ViewModelBase<State, Event>(schedulerProvider) {

    private fun createState(previous: State): Single<State> {
        val clickCount = previous.totalClicks + 1
        return when (clickCount) {
            in 1..4 -> Single.just(State.HiddenToast(clickCount))
            in 5..9 -> Single.just(State.ShownToast(clickCount))
            else    -> showDebugSettings()
        }
    }

    private fun showDebugSettings(): Single<State> = Single.fromCallable {
        intentLauncher.launch(DeveloperActivity::class.java)
        State.HiddenToast(0)
    }

    override fun reduce(previous: State, event: Event): Single<State> = when (event) {
        Event.LongClick  -> showDebugSettings()
        Event.ShortClick -> createState(previous)
    }

    class Factory constructor(
        private val schedulerProvider: SchedulerProvider,
        private val intentLauncher: Router
                             ) :
        ViewModelProvider.Factory {

        inline fun <reified T : ViewModel> provide(fragment: FragmentActivity) =
            ViewModelProviders.of(fragment, this).get(T::class.java)

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(schedulerProvider, intentLauncher) as T
    }

}
