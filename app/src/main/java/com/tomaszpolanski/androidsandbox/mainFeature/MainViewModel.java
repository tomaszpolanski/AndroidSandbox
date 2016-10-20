package com.tomaszpolanski.androidsandbox.mainFeature;

import com.tomaszpolanski.androidsandbox.viewmodels.AbstractViewModel;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

final class MainViewModel extends AbstractViewModel {

    @NonNull
    private final IAccelerometerProvider mAccelerometerProvider;

    MainViewModel(@NonNull final IAccelerometerProvider accelerometerProvider) {
        mAccelerometerProvider = accelerometerProvider;
    }

    @NonNull
    Flowable<String> getReadingStream() {
        return mAccelerometerProvider.getReadingStream()
                                     .map(AccelerometerReading::toString);
    }

    @NonNull
    Flowable<Long> getEventsPerSecondStream() {
        return mAccelerometerProvider.getReadingStream()
                                     .window(1, 1, TimeUnit.SECONDS)
                                     .flatMapSingle(Flowable::count);
    }

    @Override
    protected void subscribeToData(final CompositeDisposable subscription) {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }

}
