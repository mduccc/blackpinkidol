package com.indieteam.blackbinkidol.ui.events

import android.support.v4.view.ViewPager
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class ActivityEvents(val activity: MainActivity){

    fun listen(){
        activity.view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                activity.runOnUiThread {
                    when (position) {
                        0 -> activity.bottom_navigation.selectedItemId = R.id.profile
                        1 -> activity.bottom_navigation.selectedItemId = R.id.song
                        2 -> activity.bottom_navigation.selectedItemId = R.id.album
                        3 -> activity.bottom_navigation.selectedItemId = R.id.mv
                        else -> {
                        }
                    }
                }
            }
        })

        activity.bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.profile ->{
                    activity.runOnUiThread {
                        activity.view_pager.currentItem = 0
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.song ->{
                    activity.runOnUiThread {
                        activity.view_pager.currentItem = 1
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.album ->{
                    activity.runOnUiThread {
                        activity.view_pager.currentItem = 2
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.mv ->{
                    activity.runOnUiThread {
                        activity.view_pager.currentItem = 3
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener true
            }
        }

    }

}