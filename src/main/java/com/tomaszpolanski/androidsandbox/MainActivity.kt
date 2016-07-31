package com.tomaszpolanski.androidsandbox

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import polanski.option.Option
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        val self = this

        Option.ofObj(findViewById(R.id.fab))
                .ofType(FloatingActionButton::class.java)
                .get()
                ?.setOnClickListener {
                    it.showSnackbar("Here are some actions") {
                        if (Random().nextBoolean()) {
                            setAction(SnackAction.Exit(self))
                        } else {
                            setAction(SnackAction.Hide(this))
                        }

                    }
                }
    }

    fun Snackbar.setAction(action: SnackAction) =
            setAction(action.name, { executeAction(action) })

    sealed class SnackAction(val name: String) {
        data class Hide(val snackbar: Snackbar) : SnackAction("Hide")
        data class Exit(val activity: Activity) : SnackAction("Exit")
    }

    fun executeAction(action: SnackAction) =
            when (action) {
                is SnackAction.Hide -> action.snackbar.dismiss()
                is SnackAction.Exit -> action.activity.finish()
            }

    fun View.showSnackbar(text: String, init: Snackbar.() -> Unit) {
        val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
        init.invoke(snackbar)
        snackbar.show()
    }

    fun <T> Option<T>.get(): T? = match({ it }, { null })
}
