package com.tomaszpolanski.androidsandbox;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyClassTest {

    @Test
    public void someTest() {
        assertThat(EmptyClass.getNumber()).isEqualTo(9);
    }

}