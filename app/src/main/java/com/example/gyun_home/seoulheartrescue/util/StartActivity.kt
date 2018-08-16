package com.example.gyun_home.seoulheartrescue.util

import android.app.Activity
import android.content.Context
import android.content.Intent

import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View

class StartActivity {

    companion object {
        fun start(context : Context, intent : Intent, transitionView : View , isFinish : Boolean){
            var transitionName : String? = null

            if(transitionName != null){
                transitionName = ViewCompat.getTransitionName(transitionView)
            }

            if(transitionName != null && context is Activity){
                var options : ActivityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((context as Activity),transitionView,transitionName)
                context.startActivity(intent , options.toBundle())
            }else {
                context.startActivity(intent)
            }
            if(isFinish){
                if(context is Activity){
                    context.finish()
                }
            }
        }

        fun startSingle(context : Context , intent : Intent){
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

        fun startSingle(context : Context , intent : Intent,isFinish: Boolean){

            if (isFinish) {
                if (context is Activity) {
                    context.finish()
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }
}