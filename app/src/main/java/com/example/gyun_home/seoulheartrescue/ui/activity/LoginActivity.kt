package com.example.gyun_home.seoulheartrescue.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.R.id.*
import com.example.gyun_home.seoulheartrescue.ui.dialog.LodingDIalog
import com.example.gyun_home.seoulheartrescue.util.StartActivity
import com.example.gyun_home.seoulheartrescue.util.ToastMake
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import java.util.Arrays.asList



class LoginActivity : BaseActivity() {

    var authStateListener: FirebaseAuth.AuthStateListener? = null
    var lodingDIalog: LodingDIalog? = null
    private val RC_SIGN_IN = 123
    var providers = Arrays.asList(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
            )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    fun init() {

        lodingDIalog = LodingDIalog(this@LoginActivity)

        //로그인 세션 관리하는부분
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            var user = firebaseAuth.currentUser
            if (user != null) {
                //StartActivity.startSingle(this@LoginActivity,Intent(this@LoginActivity,MainActivity::class.java))
            }
        }

        loginActivity_button_login.setOnClickListener {
            if (loginactivity_editText_ID.text.toString() == "" || loginactivity_editText_Password.text.toString() == "") {
                ToastMake.make(this, "정보를 모두 입력해 주세요.")
            } else {

                lodingDIalog!!.show()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(loginactivity_editText_ID.text.toString(), loginactivity_editText_Password.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                ToastMake.make(this@LoginActivity, "로그인에 성공했습니다")
                                lodingDIalog!!.dismiss()
                                StartActivity.startSingle(this@LoginActivity,Intent(this@LoginActivity,MainActivity::class.java))
                            }
                            else
                            ToastMake.make(this@LoginActivity, task.exception.toString())
                        }
            }
        }
        loginActivity_google_login.setOnClickListener {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setLogo(R.drawable.ic_launcher_foreground)
                            .setAvailableProviders(providers)
                            .setTheme(R.style.LoginTheme)
                            .build(),
                    RC_SIGN_IN)
        }
        loginActivity_button_join.setOnClickListener {
            StartActivity.startStack(this, Intent(this@LoginActivity, JoinActivity::class.java), false)
        }
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener!!)
    }

    override fun onPause() {
        super.onPause()
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener!!)
    }
}