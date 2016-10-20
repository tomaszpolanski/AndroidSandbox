package com.tomaszpolanski.androidsandbox.mainFeature;

import com.tomaszpolanski.androidsandbox.BaseActivity;
import com.tomaszpolanski.androidsandbox.R.id;
import com.tomaszpolanski.androidsandbox.R.layout;
import com.tomaszpolanski.androidsandbox.viewmodels.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableEmitter.BackpressureMode;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import polanski.option.Unit;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.disposables.Disposables.fromAction;
import static io.reactivex.schedulers.Schedulers.computation;

public class MainActivity extends BaseActivity {

    @NonNull
    private final MainViewModel mViewModel = new MainViewModel(new AccelerometerProvider(this));

    @Nullable
    private TextView mTextView;

    @Nullable
    private TextView mTextView2;

    @Override
    protected void onBind(@NonNull CompositeDisposable d) {

        d.add(getButtonPressedStream().subscribeOn(mainThread())
                                      .subscribe(__ -> showSnackBar(),
                                                 e -> Log.e("MainActivity", "Cannot show bar", e)));

        d.add(mViewModel.getReadingStream()
                        .subscribeOn(computation())
                        .observeOn(mainThread())
                        .subscribe(reading -> mTextView.setText(reading),
                                   e -> Log.e("MainActivity", "Cannot read data", e)));

        d.add(mViewModel.getEventsPerSecondStream()
                        .subscribeOn(computation())
                        .observeOn(mainThread())
                        .subscribe(reading -> mTextView2.setText("Events per second: " + reading),
                                   e -> Log.e("MainActivity", "Cannot read data", e)));
    }

    @NonNull
    @Override
    protected ViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        mTextView = (TextView) findViewById(id.textView);
        mTextView2 = (TextView) findViewById(id.textView2);

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
