package com.tomaszpolanski.androidsandbox.viewmodels;


public interface IViewModel {

    void dispose();

    void subscribeToDataStore();

    void unsubscribeFromDataStore();
}
