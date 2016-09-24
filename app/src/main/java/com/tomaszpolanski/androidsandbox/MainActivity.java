package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.R.id;
import com.tomaszpolanski.androidsandbox.R.layout;
import com.tomaszpolanski.androidsandbox.viewmodels.AbstractViewModel;
import com.tomaszpolanski.androidsandbox.viewmodels.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableEmitter.BackpressureMode;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import polanski.option.Unit;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.disposables.Disposables.fromAction;

public class MainActivity extends BaseActivity {

    @Override
    protected void onBind(@NonNull CompositeDisposable d) {

        d.add(getButtonPressedStream().subscribeOn(mainThread())
                                      .subscribe(__ -> showSnackBar(),
                                                 e -> Log.e("MainActivity", "Cannot show bar", e)));
    }

    @NonNull
    @Override
    protected ViewModel getViewModel() {
        return new AbstractViewModel() {
            @Override
            protected void subscribeToData(final CompositeDisposable subscription) {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);

    }

    private Flowable<Unit> getButtonPressedStream() {
        return Flowable.create(new FlowableOnSubscribe<Unit>() {
            @Override
            public void subscribe(final FlowableEmitter<Unit> e) {
                if (!e.isCancelled()) {
                    FloatingActionButton fab = (FloatingActionButton) findViewById(id.fab);
                    fab.setOnClickListener(view -> e.onNext(Unit.DEFAULT));

                    e.setDisposable(fromAction(() -> fab.setOnClickListener(null)));
                }
            }
        }, BackpressureMode.DROP)
                       .share();
    }

    private void showSnackBar() {
        Snackbar.make(findViewById(id.fab), "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

}
