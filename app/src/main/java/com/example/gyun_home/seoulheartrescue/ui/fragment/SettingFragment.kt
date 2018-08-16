package com.example.gyun_home.seoulheartrescue.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.databinding.SettingBinding
import com.example.gyun_home.seoulheartrescue.viewmodel.ProfileViewModel
import com.example.gyun_home.seoulheartrescue.viewmodel.SettingViewModel

class SettingFragment : Fragment() {
    var binding : SettingBinding? = null
    var model : SettingViewModel? =null

    companion object {
        fun newInstance() : SettingFragment {
            val args = Bundle()
            val fragment = SettingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting,container,false)

        if(activity != null && activity is BaseActivity){
            model = (activity as BaseActivity).get_SettingViewModel()
            binding!!.model = model
        }
        return binding!!.root
    }
}