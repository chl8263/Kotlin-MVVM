package com.example.gyun_home.seoulheartrescue

import android.content.Context

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.gyun_home.seoulheartrescue.util.ToastMake
import com.example.gyun_home.seoulheartrescue.viewmodel.EducationViewModel
import com.example.gyun_home.seoulheartrescue.viewmodel.MainViewModel
import com.example.gyun_home.seoulheartrescue.viewmodel.ProfileViewModel
import com.example.gyun_home.seoulheartrescue.viewmodel.SettingViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {

    var inputMethodManager : InputMethodManager?  = null
    var compositeDisposable : CompositeDisposable? = null

    var mainViewModel : MainViewModel? = null
    var profileViewModel : ProfileViewModel? = null
    var settingViewModel  : SettingViewModel? = null
    var educationViewModel : EducationViewModel? = null

    val TIME_INTERVAL: Int = 2000
    var mBackpressed : Long? = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        compositeDisposable = CompositeDisposable()

    }

    fun hidekeyBoard(){
        if(inputMethodManager != null && currentFocus != null){
            inputMethodManager!!.hideSoftInputFromWindow(currentFocus.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)

        }
    }


    override fun onPause() {
        super.onPause()

        if(compositeDisposable != null){
            compositeDisposable!!.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(compositeDisposable != null){
            compositeDisposable!!.dispose()
        }
    }

    override fun onBackPressed() {


        if(mBackpressed!! + TIME_INTERVAL > System.currentTimeMillis()){
            super.onBackPressed()
            return
        }else {
            ToastMake.make(this,getString(R.string.msg_quit))
        }
        mBackpressed = System.currentTimeMillis()
    }

    fun get_MainViewModel(): MainViewModel {
        if(mainViewModel == null){
            Log.e("======","=============")
            mainViewModel = MainViewModel(this)
        }
        return mainViewModel as MainViewModel
    }
    fun get_EducationViewModel() : EducationViewModel{
        if (educationViewModel == null) {
            educationViewModel = EducationViewModel(this)
        }
        return educationViewModel as EducationViewModel
    }

    fun get_ProfileViewModel(): ProfileViewModel {
        if (profileViewModel == null) {
            profileViewModel = ProfileViewModel(this)
        }
        return profileViewModel as ProfileViewModel
    }

    fun get_SettingViewModel(): SettingViewModel {
        if (settingViewModel == null) {
            settingViewModel = SettingViewModel(this)
        }
        return settingViewModel as SettingViewModel
    }
}





























