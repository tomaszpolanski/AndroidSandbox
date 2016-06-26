package com.tomaszpolanski.androidsandbox;

// TODO remove this and use from vm
public interface IViewModel {
    void dispose();

    void subscribeToDataStore();

    void unsubscribeFromDataStore();
}
