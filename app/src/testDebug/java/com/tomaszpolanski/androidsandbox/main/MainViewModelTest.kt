package com.tomaszpolanski.androidsandbox.main


import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test


class MainViewModelTest {
    @Before
    fun setUp() {
        (1..10).asSequence().filter { it % 2 == 0 }
                .all { it % 2 == 0 }
    }

    @Test
    fun test() {
        Assertions.assertThat(2).isEqualTo(4)
    }
}