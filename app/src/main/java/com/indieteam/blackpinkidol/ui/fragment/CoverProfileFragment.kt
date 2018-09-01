package com.indieteam.blackpinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_cover_profile.*


class CoverProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cover_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image_cover_profile.setImageResource(R.drawable.cover_blackpink_profile)
        cover_profile.layoutParams.height = ((context as MainActivity).sY*30).toInt()
        image_cover_profile.scaleType = ImageView.ScaleType.CENTER_CROP
        rl_cover_profile.layoutParams.height = ((context as MainActivity).sY*30).toInt() + ((activity as MainActivity).sY*5).toInt()/2    }
}

