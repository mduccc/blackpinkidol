package com.indieteam.blackbinkidol.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val demoData = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
            gird_view.let {
                it.adapter = GirdviewAdapter(activity!!, demoData)
                it.y = (activity as MainActivity).sY*43
                //it.columnWidth = ((activity as MainActivity).sX*30).toInt()
                it.verticalSpacing = ((activity as MainActivity).sX*15).toInt()
                it.layoutParams.height = (((activity as MainActivity).sY*100).toInt() - (activity as MainActivity).sY*65).toInt()
            }
        }
    }
}
