package com.tomaszpolanski.androidsandbox.viewmodels;

import io.reactivex.disposables.CompositeDisposable;
import polanski.option.AtomicOption;
import polanski.option.Option;

public abstract class AbstractViewModel implements ViewModel {

    private final AtomicOption<CompositeDisposable> mDisposables = new AtomicOption<>();

    @Override
    public void dispose() {
        unsubscribeFromDataStore();
    }

    @Override
    public final void subscribeToDataStore() {
        unsubscribeFromDataStore();
        mDisposables.set(Option.ofObj(new CompositeDisposable()));
        mDisposables.get().ifSome(this::subscribeToData);
    }

    @Override
    public void unsubscribeFromDataStore() {
        mDisposables.getAndSet(Option.none())
                    .ifSome(CompositeDisposable::clear);
    }

    protected abstract void subscribeToData(CompositeDisposable subscription);

    private Option<CompositeDisposable> getDisposables() {
        return mDisposables.get();
    }

}
