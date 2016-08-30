package com.tomaszpolanski.androidsandbox;

import com.tomaszpolanski.androidsandbox.viewmodels.AbstractViewModel;
import com.tomaszpolanski.androidsandbox.viewmodels.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposables;
import polanski.option.Unit;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private Observable<Unit> getButtonPressedStream() {
        return Observable.create(new ObservableOnSubscribe<Unit>() {
            @Override
            public void subscribe(final ObservableEmitter<Unit> e) {
                if (!e.isCancelled()) {
                    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                    fab.setOnClickListener(view -> e.onNext(Unit.DEFAULT));

                    e.setDisposable(
                            Disposables.from((Runnable) () -> fab.setOnClickListener(null)));
                }
            }
        }).share();
    }

    private void showSnackBar() {
        Snackbar.make(findViewById(R.id.fab), "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }

}
