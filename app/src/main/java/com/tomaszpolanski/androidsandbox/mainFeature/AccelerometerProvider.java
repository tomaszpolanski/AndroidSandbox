package com.tomaszpolanski.androidsandbox.mainFeature;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter.BackpressureMode;
import io.reactivex.disposables.Disposables;

final class AccelerometerProvider implements IAccelerometerProvider {

    @NonNull
    private final Flowable<AccelerometerReading> mReadingStream;

    AccelerometerProvider(@NonNull final Context context) {
        mReadingStream = getAccelerometerReading(context)
                .share();
    }

    @Override
    @NonNull
    public Flowable<AccelerometerReading> getReadingStream() {
        return mReadingStream;
    }

    @NonNull
    private static Flowable<AccelerometerReading> getAccelerometerReading(
            @NonNull final Context context) {
        return Flowable.create(e -> {
            SensorManager manager = (SensorManager) context
                    .getSystemService(Context.SENSOR_SERVICE);
            if (manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

                SensorEventListener listener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(final SensorEvent event) {
                        AccelerometerReading reading = new AccelerometerReading(event.values[0],
                                                                                event.values[1],
                                                                                event.values[2]);

                        e.onNext(reading);
                    }

                    @Override
                    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

                    }
                };

                manager.registerListener(
                        listener, sensor,
                        SensorManager.SENSOR_DELAY_GAME);
                e.setDisposable(Disposables.fromAction(() -> manager.unregisterListener(listener)));
            } else {
                e.onError(new RuntimeException("Cannot find accelerometer sensor."));
            }
        }, BackpressureMode.ERROR);
    }

}
