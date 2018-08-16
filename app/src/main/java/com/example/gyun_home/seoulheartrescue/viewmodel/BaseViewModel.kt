package com.example.gyun_home.seoulheartrescue.viewmodel

import android.app.Activity
import android.content.Context
import android.databinding.BaseObservable
import android.util.Log
import com.example.gyun_home.seoulheartrescue.BaseActivity

open class BaseViewModel (context : Context) : BaseObservable(){

    var mContext : Context? = null

    init {
        Log.e("BaseViewModel ", "BaseViewModelBaseViewModel")
        mContext = context
    }

    /*constructor(context : Context){
        mContext = context
    }*/

    fun hideKeyBoard(){
        if(mContext is BaseActivity){
            (mContext as BaseActivity).hidekeyBoard()
        }
    }

    fun close (){
        if(mContext is Activity){
            (mContext as Activity).onBackPressed()
        }
        hideKeyBoard()
    }


}