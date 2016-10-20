package com.tomaszpolanski.androidsandbox.mainFeature;

import com.tomaszpolanski.androidsandbox.viewmodels.AbstractViewModel;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

final class MainViewModel extends AbstractViewModel {

    @NonNull
    private final IAccelerometerProvider mAccelerometerProvider;

    @NonNull
    private Flowable<Long> mEventsPerSecondStream;

    @NonNull
    private Flowable<String> mReadingStream;

    MainViewModel(@NonNull final IAccelerometerProvider accelerometerProvider) {
        mAccelerometerProvider = accelerometerProvider;
    }

    @NonNull
    Flowable<String> getReadingStream() {
        return mReadingStream;
    }

    @NonNull
    Flowable<Long> getEventsPerSecondStream() {
        return mEventsPerSecondStream;
    }

    @Override
    protected void subscribeToData(final CompositeDisposable subscription) {
        Flowable<AccelerometerProvider.AccelerometerReading> readings = mAccelerometerProvider
                .getReadingStream()
                .share();

        mEventsPerSecondStream = readings
                .window(1, 1, TimeUnit.SECONDS)
                .flatMapSingle(Flowable::count);

        mReadingStream = readings.map(AccelerometerProvider.AccelerometerReading::toString);
    }

    @Override
    public boolean isDisposed() {
        return false;
    }

}
