package com.tomaszpolanski.androidsandbox.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.tomaszpolanski.androidsandbox.App
import com.tomaszpolanski.androidsandbox.R
import com.tomaszpolanski.androidsandbox.core.BindingBaseActivity
import com.tomaszpolanski.androidsandbox.inject.activity.BaseActivityModule
import com.tomaszpolanski.androidsandbox.model.User
import com.tomaszpolanski.androidsandbox.persistance.AppDatabase
import com.tomaszpolanski.androidsandbox.viewmodel.DataBinder
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BindingBaseActivity<MainActivityComponent>() {

    @Inject
    internal lateinit var database: AppDatabase

    @Inject
    internal lateinit var vm: MainViewModel

    private val dataBinder = object : DataBinder {
        override fun bind(disposables: CompositeDisposable) {

        }

        override fun unbind() {

        }
    }

    override fun inject() {
        component().inject(this)
    }

    override fun createComponent(): MainActivityComponent {
        return DaggerMainActivityComponent.builder()
            .applicationComponent((application as App).component())
            .baseActivityModule(BaseActivityModule(this))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database.userProvider().apply {
            Completable.fromAction { insertAll(User("Tomek")) }
                .subscribeOn(Schedulers.io())
                .subscribe()

            getAllAuthor().subscribeOn(Schedulers.io())
                .subscribe({ Log.e("QQQ1", it.toString()) },
                           { Log.e("QQQ2", it.toString()) })
        }

    }

    override fun viewModel() = vm

    override fun dataBinder() = dataBinder
}
