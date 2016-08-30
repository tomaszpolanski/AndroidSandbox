package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.viewmodels.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AppCompatActivity {

    @NonNull
    private final ILifecycleBindableView mBindableView = new LifecycleBindableView() {
        @Override
        public void onBindBinder(@NonNull final CompositeDisposable d) {
            onBind(d);
        }

        @NonNull
        @Override
        public ViewModel getBinderViewModel() {
            return getViewModel();
        }
    };

    protected abstract void onBind(@NonNull final CompositeDisposable subscription);

    @NonNull
    protected abstract ViewModel getViewModel();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mBindableView.onCreated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBindableView.onResume();
    }

    @Override
    protected void onPause() {
        mBindableView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mBindableView.onDestroy();
        super.onDestroy();
    }
}
