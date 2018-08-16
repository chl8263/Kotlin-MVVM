package com.example.gyun_home.seoulheartrescue.viewmodel.bindingAdapter

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import android.util.Log

object NaviagationViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("setSelectedItemId")
    fun setSelecteditemId(view: BottomNavigationView, itemId: Int) {
        if(view == null) {
            Log.e("selected is null", "null")
        }
        view.selectedItemId = itemId
    }

    @JvmStatic
    @BindingAdapter("setOnNavigationItemSelected")
    fun setOnNavigationItemSelectedListener(view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener?) {
        if (view != null && listener != null) {
            view.setOnNavigationItemSelectedListener(listener)
        }

        if (view == null) {
            Log.e("view is null","null")
        }
        if (listener == null) {
            Log.e("listener is null","null")
        }
    }

}