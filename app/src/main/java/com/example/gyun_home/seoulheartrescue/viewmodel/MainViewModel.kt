package com.example.gyun_home.seoulheartrescue.viewmodel

import android.content.Context
import android.support.design.internal.BottomNavigationItemView
import android.support.design.widget.BottomNavigationView
import android.util.Log

open class MainViewModel(context : Context): BaseViewModel(context) {



    var mNavigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener? = null

    /*constructor(context : Context){
        super(context)
    }*/

    fun setNavigationItemSelectedListener (mNavigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener ){
        if(mNavigationItemSelectedListener != null){
            Log.e("ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ","ㅁㅁㅁ")
        }
        this.mNavigationItemSelectedListener = mNavigationItemSelectedListener
    }


}