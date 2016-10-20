package com.tomaszpolanski.androidsandbox.mainFeature;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposables;

class AccelerometerProvider implements IAccelerometerProvider {

    @NonNull
    private final Context mContext;

    AccelerometerProvider(@NonNull final Context context) {
        mContext = context;
    }

    @Override
    @NonNull
    public Flowable<AccelerometerReading> getReadingStream() {
        return Flowable.create(e -> {
            SensorManager manager = (SensorManager) mContext
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
        }, FlowableEmitter.BackpressureMode.ERROR);
    }

    static final class AccelerometerReading {

        private final float mX;

        private final float mY;

        private final float mZ;

        AccelerometerReading(float x, float y, float z) {
            mX = x;
            mY = y;
            mZ = z;
        }

        public float getX() {
            return mX;
        }

        public float getY() {
            return mY;
        }

        public float getZ() {
            return mZ;
        }

        @Override
        public String toString() {
            return "x: " + mX +
                   ", y: " + mY +
                   ", z: " + mZ;
        }
    }
}
