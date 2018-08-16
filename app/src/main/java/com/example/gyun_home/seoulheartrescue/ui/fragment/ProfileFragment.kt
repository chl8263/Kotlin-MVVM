package com.example.gyun_home.seoulheartrescue.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.databinding.EducationBinding
import com.example.gyun_home.seoulheartrescue.databinding.ProfileBinding
import com.example.gyun_home.seoulheartrescue.viewmodel.EducationViewModel
import com.example.gyun_home.seoulheartrescue.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(){

    var binding : ProfileBinding? = null
    var model : ProfileViewModel? =null

    companion object {
        fun newInstance() : ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmnet_profile,container,false)

        if(activity != null && activity is BaseActivity){
            model = (activity as BaseActivity).get_ProfileViewModel()
            binding!!.model = model
        }
        return binding!!.root
    }
}