package com.tomaszpolanski.androidsandbox.viewmodels;

import polanski.option.Option;
import rx.subscriptions.CompositeSubscription;

public abstract class AbstractViewModel implements IViewModel {

    private AtomicOption<CompositeSubscription> mSubscriptions = new AtomicOption<>();

    @Override
    public void dispose() {
        unsubscribeFromDataStore();
    }

    @Override
    public final void subscribeToDataStore() {
        unsubscribeFromDataStore();
        mSubscriptions.set(Option.ofObj(new CompositeSubscription()));
        mSubscriptions.get().ifSome(this::subscribeToData);
    }

    @Override
    public void unsubscribeFromDataStore() {
        mSubscriptions.getAndSet(Option.none())
                .ifSome(CompositeSubscription::clear);
    }

    protected abstract void subscribeToData(CompositeSubscription subscription);

    private Option<CompositeSubscription> getSubscriptions() {
        return mSubscriptions.get();
    }

}
