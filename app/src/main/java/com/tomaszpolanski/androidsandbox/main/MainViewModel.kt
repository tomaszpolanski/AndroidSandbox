package com.tomaszpolanski.androidsandbox.main

import com.tomaszpolanski.androidsandbox.viewmodel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : BaseViewModel() {


    override fun bind(disposables: CompositeDisposable) {

    }

    override fun unbind() {

    }

    fun something(list: String): String {
        return list.map { match(it) }
                .reversed()
                .joinToString(separator = "")
    }

    fun match(character: Char) : Char {
        return when (character) {
            'a' -> 'b'
            'b' -> 'a'
            else -> ' '
        }
    }

}
