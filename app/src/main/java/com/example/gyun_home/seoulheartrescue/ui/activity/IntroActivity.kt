package com.example.gyun_home.seoulheartrescue.ui.activity

import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.databinding.IntroBinding
import com.example.gyun_home.seoulheartrescue.util.StartActivity
import com.firebase.ui.auth.AuthUI
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.R.attr.data
import android.app.Activity
import com.example.gyun_home.seoulheartrescue.util.ToastMake
import com.firebase.ui.auth.IdpResponse



class IntroActivity : BaseActivity() {

    var binding: IntroBinding? = null

    private val RC_SIGN_IN = 123
    var providers = Arrays.asList(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
    )


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
                   /* StartActivity.startSingle(this@IntroActivity
                            , Intent(this@IntroActivity, MainActivity::class.java),true)*/

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setLogo(R.drawable.ic_launcher_foreground)
                                    .setAvailableProviders(providers)
                                    .setTheme(R.style.AppTheme)
                                    .build(),
                            RC_SIGN_IN)
                }))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode === Activity.RESULT_OK) {
                // Successfully signed in
                StartActivity.startSingle(this@IntroActivity,Intent(this@IntroActivity,MainActivity::class.java))
            } else {
                ToastMake.make(this@IntroActivity,data.toString())
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setLogo(R.drawable.ic_launcher_foreground)
                                .setAvailableProviders(providers)
                                .setTheme(R.style.LoginTheme)
                                .build(),
                        RC_SIGN_IN)
            }
        }
    }
}
