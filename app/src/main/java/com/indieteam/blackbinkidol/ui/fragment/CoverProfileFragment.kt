package com.indieteam.blackbinkidol.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.R
import kotlinx.android.synthetic.main.fragment_cover_profile.*


class CoverProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cover_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cover_profile.layoutParams.height = ((context as MainActivity).sY*30).toInt()
        image_cover_profile.scaleType = ImageView.ScaleType.CENTER_CROP

//        val title = TextView(activity!!)
//        title.text = "BLΛƆKPIИK"
//        title.textSize = 14f
//        title.gravity = Gravity.CENTER
//        title.typeface = Typeface.DEFAULT_BOLD
//        title.setTextColor(resources.getColor(R.color.colorWhite))
//        title.background = resources.getDrawable(R.drawable.boder_title)
//        rl_cover_profile.addView(title)
//        title.layoutParams.width = ((activity as MainActivity).sX*45).toInt()
//        title.layoutParams.height = ((activity as MainActivity).sY*5).toInt()
//        title.y = (activity as MainActivity).sY*30 - ((activity as MainActivity).sY*5).toInt()
//        title.x = (activity as MainActivity).sX*50 - ((activity as MainActivity).sX*45).toInt()/2

        rl_cover_profile.layoutParams.height = ((context as MainActivity).sY*30).toInt() + ((activity as MainActivity).sY*5).toInt()/2
    }
}

