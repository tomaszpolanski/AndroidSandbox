package com.tomaszpolanski.androidsandbox;

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
