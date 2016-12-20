package com.tomaszpolanski.androidsandbox.mainFeature;

final class AccelerometerReading {

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
               "\r\ny: " + mY +
               "\r\nz: " + mZ;
    }
}
