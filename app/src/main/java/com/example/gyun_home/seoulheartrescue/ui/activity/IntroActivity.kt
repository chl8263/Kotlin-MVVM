package com.example.gyun_home.seoulheartrescue.ui.activity

import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.databinding.IntroBinding
import com.example.gyun_home.seoulheartrescue.util.StartActivity
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class IntroActivity : BaseActivity() {

    var binding: IntroBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    override fun onResume() {
        super.onResume()

        compositeDisposable!!.add(Single.timer(2500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(io.reactivex.functions.Consumer {t ->
                    StartActivity.startSingle(this@IntroActivity
                            , Intent(this@IntroActivity, MainActivity::class.java),true)
                }))


    }




}
