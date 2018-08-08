package com.indieteam.blackbinkidol.fragment

import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.activity.MainActivity
import com.indieteam.blackbinkidol.adapter.GirdviewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
            it.supportFragmentManager.beginTransaction().add(R.id.rl_profile_fragment, CoverProfileFragment(), "cover_profile")
                .commit()
            gird_view.let {
                it.adapter = GirdviewAdapter(activity!!, (activity as MainActivity).idol)
                it.y = (activity as MainActivity).sY*30 + ((activity as MainActivity).sY*5)/2
                //it.columnWidth = ((activity as MainActivity).sX*30).toInt()
                //it.verticalSpacing = ((activity as MainActivity).sX*15).toInt()
                activity!!.bottom_navigation.measure(0,0)
                it.layoutParams.height = ((((activity as MainActivity).sY*100).toInt() - (activity as MainActivity).sY*30).toInt() - activity!!.bottom_navigation.measuredHeight - Math.round(((context as MainActivity).sY*10/2).toDouble())).toInt()
            }
        }
    }
}
