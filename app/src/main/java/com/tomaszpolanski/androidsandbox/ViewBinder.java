package com.tomaszpolanski.androidsandbox;

import polanski.option.Option;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static polanski.option.Option.none;
import static polanski.option.Option.ofObj;


public class ViewBinder {

    private AtomicOption<CompositeSubscription> mSubscriptions = new AtomicOption<>();

    private final Action1<CompositeSubscription> mOnBindAction;

    public ViewBinder(Action1<CompositeSubscription> onBindAction) {
        mOnBindAction = onBindAction;
    }

    public void bind() {
        mSubscriptions.compareAndSet(none(), ofObj(new CompositeSubscription()));
        mSubscriptions.get().ifSome(this::onBind);
    }

    public void unbind() {
        mSubscriptions.getAndSet(Option.none())
                .ifSome(CompositeSubscription::clear);
    }

    protected void onBind(final CompositeSubscription subscription) {
        mOnBindAction.call(subscription);
    }
}
