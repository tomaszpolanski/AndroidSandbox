package com.tomaszpolanski.androidsandbox

import android.content.Context
import android.content.Intent

interface Router {
    fun launch(cls: Class<*>)
}

class RouterImpl(private val context: Context) : Router {
    override fun launch(cls: Class<*>) {
        context.startActivity(Intent(context, cls))
    }
}
