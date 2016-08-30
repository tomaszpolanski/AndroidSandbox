package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.viewmodels.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import io.reactivex.disposables.CompositeDisposable;
import polanski.option.Option;

public class MainActivity extends BaseActivity {

    @Override
    protected void onBind(@NonNull CompositeDisposable subscription) {

    }

    @NonNull
    @Override
    protected ViewModel getViewModel() {
        return new ViewModel() {
            @Override
            public void dispose() {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }

            @Override
            public void subscribeToDataStore() {

            }

            @Override
            public void unsubscribeFromDataStore() {

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Option<FloatingActionButton> fab = Option.ofObj(findViewById(R.id.fab))
                                                 .ofType(FloatingActionButton.class);

        fab.ifSome(button ->
                           button.setOnClickListener(view -> Snackbar
                                   .make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show()));
    }

}
