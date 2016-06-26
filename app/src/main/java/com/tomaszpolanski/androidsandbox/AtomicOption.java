package com.tomaszpolanski.androidsandbox;

import java.util.concurrent.atomic.AtomicReference;

import polanski.option.Option;

// TODO remove this and use from vm
public final class AtomicOption<T> extends AtomicReference<Option<T>> {

    public AtomicOption() {
        super(Option.none());
    }
}
