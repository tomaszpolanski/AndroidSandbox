package com.tomaszpolanski.androidsandbox.viewmodels;

import io.reactivex.disposables.Disposable;

public interface ViewModel extends Disposable {

    void subscribeToDataStore();

    void unsubscribeFromDataStore();
}
