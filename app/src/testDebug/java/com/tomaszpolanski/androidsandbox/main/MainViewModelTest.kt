package com.tomaszpolanski.androidsandbox.main


import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class MainViewModelTest {
    @Before
    fun setUp() {

    }

    @Test
    fun test() {
        assertThat(MainViewModel().something("aab")).isEqualTo("abb")
    }
}