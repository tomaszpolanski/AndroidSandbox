package com.tomaszpolanski.androidsandbox.mainFeature;

import io.reactivex.Flowable;

interface IAccelerometerProvider {

    Flowable<AccelerometerProvider.AccelerometerReading> getReadingStream();
}
