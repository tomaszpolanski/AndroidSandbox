package com.tomaszpolanski.androidsandbox.viewmodels;

import java.util.concurrent.atomic.AtomicReference;

import polanski.option.Option;

public final class AtomicOption<T> extends AtomicReference<Option<T>> {

    public AtomicOption() {
        super(Option.none());
    }
}
