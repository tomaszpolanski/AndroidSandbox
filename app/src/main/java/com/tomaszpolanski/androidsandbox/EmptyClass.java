package com.tomaszpolanski.androidsandbox;

import ix.Ix;

public class EmptyClass {

    static int getNumber() {
        return Ix.fromArray(1, 3, 5)
                .reduce((i1, i2) -> i1 + i2)
                .single();
    }
}
