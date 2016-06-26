package com.tomaszpolanski.androidsandbox;

public abstract class LifecycleBindableView implements ILifecycleBindableView {

    private boolean mInitialised;

    private final ViewBinder mViewBinder;

    protected LifecycleBindableView(final ViewBinder viewBinder) {
        mViewBinder = viewBinder;
    }

    protected LifecycleBindableView() {
        mViewBinder = new ViewBinder(this::onBindBinder);
    }

    @Override
    public void onCreated() {
        getBinderViewModel().subscribeToDataStore();
        mInitialised = true;
    }

    @Override
    public void onResume() {
        mViewBinder.bind();
    }

    @Override
    public void onPause() {
        mViewBinder.unbind();
    }

    @Override
    public void onDestroyView() {
        getBinderViewModel().unsubscribeFromDataStore();
    }

    @Override
    public void onDestroy() {
        if (mInitialised) {
            // due to Fragment's lifecycle onDestroy can be called without
            // initialising onActivityCreated
            getBinderViewModel().dispose();
            mInitialised = false;
        }
    }

}
