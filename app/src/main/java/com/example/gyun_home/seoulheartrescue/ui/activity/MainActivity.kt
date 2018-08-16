package com.example.gyun_home.seoulheartrescue.ui.activity

import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.setContentView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.MenuItem
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.databinding.MainBinding

import com.example.gyun_home.seoulheartrescue.ui.fragment.EducationFragment
import com.example.gyun_home.seoulheartrescue.ui.fragment.ProfileFragment
import com.example.gyun_home.seoulheartrescue.ui.fragment.SettingFragment
import com.example.gyun_home.seoulheartrescue.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    var binding: MainBinding? = null
    var model: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        model = get_MainViewModel()
        model!!.setNavigationItemSelectedListener(navigationItemSelectedListener)

        binding!!.model = model
        //navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    fun replaceFragment(fragment: Fragment) {
        var ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment).commit()
    }

    var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        menuItem ->
        when(menuItem.itemId){
            R.id.navigation_education -> {
                Log.e("aaa","aaa")

                replaceFragment(EducationFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                Log.e("aaa","aaa")

                replaceFragment(ProfileFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                Log.e("aaa","aaa")
                replaceFragment(SettingFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            else -> false
        }
        return@OnNavigationItemSelectedListener false

    }

    /*var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_education -> {
                replaceFramgment(EducationFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                replaceFramgment(ProfileFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                replaceFramgment(SettingFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }*/


     /*var navigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener
             = object : BottomNavigationView.OnNavigationItemSelectedListener{
         override fun onNavigationItemSelected(p0: MenuItem): Boolean {
             when(p0.itemId){
                 R.id.navigation_education -> {
                     Log.e("aaa","aaa")
                     replaceFragment(EducationFragment.newInstance())
                     return true
                 }
                 R.id.navigation_profile -> {
                     Log.e("aaa","aaa22222")
                     replaceFragment(ProfileFragment.newInstance())
                     return true
                 }
                 R.id.navigation_setting -> {
                     Log.e("aaa","aaa333")
                     replaceFragment(SettingFragment.newInstance())
                     return true
                 }
                 else -> false
             }
             return false
         }

     }*/
}
