package com.example.gyun_home.seoulheartrescue.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.databinding.HistoryBinding
import com.example.gyun_home.seoulheartrescue.viewmodel.HistoryViewModel

class HistoryFragment : Fragment() {
    var binding : HistoryBinding? = null
    var model : HistoryViewModel? =null

    companion object {
        fun newInstance() : HistoryFragment {
            val args = Bundle()
            val fragment = HistoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history,container,false)

        if(activity != null && activity is BaseActivity){
            model = (activity as BaseActivity).get_HistoryViewModel()
            binding!!.model = model
        }
        return binding!!.root
    }
}