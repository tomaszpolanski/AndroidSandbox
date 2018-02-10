package com.tomaszpolanski.androidsandbox

import android.os.Bundle
import android.widget.Toast
import com.tomaszpolanski.androidsandbox.Event.LongClick
import com.tomaszpolanski.androidsandbox.Event.ShortClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ActivityIntent<State, Event>() {

    private lateinit var hiddenTip: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        hiddenTip = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        viewModel = pretendedInjection()
        bind(savedInstanceState, State.HiddenToast(0))
    }

    override fun onBind() {
        fab.run {
            setOnClickListener {
                intent(ShortClick)
            }
            setOnLongClickListener {
                intent(LongClick)
                true
            }
        }
    }

    private fun pretendedInjection() =
        MainViewModel.Factory(
            DefaultSchedulerProvider(),
            RouterImpl(this)
                             )
            .provide<MainViewModel>(this)


    override fun updateState(state: State) {
        super.updateState(state)
        return when (state) {
            is State.HiddenToast -> hiddenTip.cancel()
            is State.ShownToast  -> with(hiddenTip) {
                setText(state.text)
                show()
            }
        }
    }
}
