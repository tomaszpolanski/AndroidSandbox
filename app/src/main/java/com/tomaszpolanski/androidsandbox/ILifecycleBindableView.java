package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.viewmodels.IViewModel;

import rx.subscriptions.CompositeSubscription;

public interface ILifecycleBindableView {

    void onCreated();

    void onResume();

    void onPause();

    void onDestroyView();

    void onDestroy();

    void onBindBinder(CompositeSubscription subscription);

    IViewModel getBinderViewModel();
}
