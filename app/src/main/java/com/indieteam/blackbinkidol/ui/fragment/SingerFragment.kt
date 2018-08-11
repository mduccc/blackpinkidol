package com.indieteam.blackbinkidol.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast

import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.process.Singer
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cover_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_singer.*

class SingerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        activity?.let {
            it as MainActivity
            Singer(it).get(key as String)
            it.bottom_navigation.measure(0, 0)
            rl_singer_fragment.layoutParams.height = (it.sY * 100 - it.rl_cover_profile.height - it.bottom_navigation.measuredHeight - it.navigationBarHeight + it.statusBarHeight).toInt()
            rl_singer_fragment.y = it.rl_cover_profile.height.toFloat()
        }
    }
}
