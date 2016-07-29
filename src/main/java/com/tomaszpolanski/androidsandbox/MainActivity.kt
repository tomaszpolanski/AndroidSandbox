package com.tomaszpolanski.androidsandbox

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import polanski.option.Option

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)

        Option.ofObj(findViewById(R.id.fab))
                .ofType(FloatingActionButton::class.java)
                .get()
                ?.setOnClickListener {
                    Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }

    }

    fun <T> Option<T>.get(): T? = match({ it }, { null })

}
