package com.tomaszpolanski.androidsandbox.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tomaszpolanski.androidsandbox.App;
import com.tomaszpolanski.androidsandbox.R;
import com.tomaszpolanski.androidsandbox.core.BindingBaseActivity;
import com.tomaszpolanski.androidsandbox.inject.activity.BaseActivityModule;
import com.tomaszpolanski.androidsandbox.viewmodel.DataBinder;
import com.tomaszpolanski.androidsandbox.viewmodel.ViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends BindingBaseActivity<MainActivityComponent> {

    @Inject
    MainViewModel vm;

    @BindView(R.id.progressBar)
    View progressBar;

    @BindView(R.id.textView)
    TextView errorView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private final DataBinder dataBinder = new DataBinder() {
        @Override
        public void bind(@NonNull CompositeDisposable disposables) {

        }

        @Override
        public void unbind() {

        }
    };


    @Override
    public void inject() {
        component().inject(this);
    }

    @NonNull
    @Override
    protected MainActivityComponent createComponent() {
        return DaggerMainActivityComponent.builder()
                .applicationComponent(((App) getApplication()).component())
                .baseActivityModule(new BaseActivityModule(this))
                .build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    protected ViewModel viewModel() {
        return vm;
    }

    @NonNull
    @Override
    protected DataBinder dataBinder() {
        return dataBinder;
    }
}
