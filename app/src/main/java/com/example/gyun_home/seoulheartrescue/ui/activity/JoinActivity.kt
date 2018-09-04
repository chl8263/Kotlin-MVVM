package com.example.gyun_home.seoulheartrescue.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.ui.dialog.LodingDIalog
import com.example.gyun_home.seoulheartrescue.util.ToastMake
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    var lodingDIalog : LodingDIalog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        init()
    }

    fun init(){
        lodingDIalog = LodingDIalog(this@JoinActivity)

        joinactivity_button_ok.setOnClickListener {
            createEmailId()
        }
    }

    fun createEmailId(){

        if(joinactivity_editText_id.text.toString() == "" || joinactivity_editText_password.text.toString() == ""){
            ToastMake.make(this,"정보를 모두 입력해 주세요.")
        }else {

            lodingDIalog!!.show()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(joinactivity_editText_id.text.toString(), joinactivity_editText_password.text.toString())
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            ToastMake.make(this, "회원가입에 성공하였습니다.")
                            lodingDIalog!!.dismiss()
                            finish()
                        } else {
                            ToastMake.make(this, task.exception.toString())
                        }

                    }
        }
    }
}
