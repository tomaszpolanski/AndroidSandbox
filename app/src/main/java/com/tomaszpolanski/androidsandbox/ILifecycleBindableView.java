package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.viewmodels.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public interface ILifecycleBindableView {

    void onCreated();

    void onResume();

    void onPause();

    void onDestroyView();

    void onDestroy();

    void onBindBinder(CompositeDisposable disposables);

    ViewModel getBinderViewModel();
}
