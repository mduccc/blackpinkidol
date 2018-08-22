package com.indieteam.blackpinkidol.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewpagerAdapter(fragmentManager: FragmentManager, val layout: List<Fragment>): FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        return layout[position]
    }

    override fun getCount(): Int {
        return layout.size
    }

}