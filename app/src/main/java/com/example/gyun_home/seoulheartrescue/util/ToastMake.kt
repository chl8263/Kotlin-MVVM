package com.example.gyun_home.seoulheartrescue.util

import android.content.Context
import android.widget.Toast

class ToastMake {

    companion object {

        fun make (context:Context, id: Int){
            try {
                Toast.makeText(context,context.getString(id),Toast.LENGTH_SHORT).show()
            }catch (e : Exception){
                e.printStackTrace()
            }
        }

        fun make (context:Context, text: String){
            try {
                Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
            }catch (e : Exception){
                e.printStackTrace()
            }
        }

        fun error (context : Context , text : String){
            try {
                Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}