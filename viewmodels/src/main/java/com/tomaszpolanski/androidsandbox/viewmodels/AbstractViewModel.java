package com.tomaszpolanski.androidsandbox.viewmodels;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;


public abstract class AbstractViewModel implements IViewModel {

    private CompositeSubscription mSubscriptions;

    @Override
    public void dispose() {
        unsubscribeFromDataStore();
    }

    @Override
    public final void subscribeToDataStore() {
        unsubscribeFromDataStore();
        mSubscriptions = new CompositeSubscription();
        subscribeToData(mSubscriptions);
    }

    @Override
    public void unsubscribeFromDataStore() {
        if (mSubscriptions != null) {
            mSubscriptions.clear();
            mSubscriptions = null;
        }
    }

    protected abstract void subscribeToData(@NonNull CompositeSubscription subscription);

    @Nullable
    private CompositeSubscription getSubscriptions() {
        return mSubscriptions;
    }

}
