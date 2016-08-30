package com.tomaszpolanski.androidsandbox;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import polanski.option.AtomicOption;

import static polanski.option.Option.none;
import static polanski.option.Option.ofObj;

public class ViewBinder {

    private final AtomicOption<CompositeDisposable> mDisposables = new AtomicOption<>();

    private final Consumer<CompositeDisposable> mOnBindAction;

    public ViewBinder(Consumer<CompositeDisposable> onBindAction) {
        mOnBindAction = onBindAction;
    }

    public void bind() {
        mDisposables.compareAndSet(none(), ofObj(new CompositeDisposable()));
        mDisposables.get().ifSome(this::onBind);
    }

    public void unbind() {
        mDisposables.getAndSet(none())
                    .ifSome(CompositeDisposable::clear);
    }

    protected void onBind(final CompositeDisposable subscription) {
        try {
            mOnBindAction.accept(subscription);
        } catch (Exception e) {
        }
    }
}
