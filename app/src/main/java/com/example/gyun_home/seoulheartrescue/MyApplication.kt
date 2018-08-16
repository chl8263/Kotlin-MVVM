package com.example.gyun_home.seoulheartrescue

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

class MyApplication : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}