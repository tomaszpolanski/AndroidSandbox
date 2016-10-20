package com.tomaszpolanski.androidsandbox.mainFeature;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;

interface IAccelerometerProvider {

    @NonNull
    Flowable<AccelerometerReading> getReadingStream();
}
