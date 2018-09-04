package com.example.gyun_home.seoulheartrescue.ui.dialog

import android.app.ProgressDialog
import android.content.Context
import android.databinding.DataBindingUtil.setContentView
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.animation.AnimationUtils
import com.example.gyun_home.seoulheartrescue.R
import kotlinx.android.synthetic.main.activity_loding_dialog.*

class LodingDIalog(context : Context) : ProgressDialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        setCanceledOnTouchOutside(false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loding_dialog)
        init()
    }

    fun init(){
        window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        var animation = AnimationUtils.loadAnimation(context,R.anim.loding)
        loginDialog_imageView.animation = animation
    }

    override fun show() {
        super.show()
    }

    override fun dismiss() {
        super.dismiss()
    }
}
