package com.indieteam.blackpinkidol.ui.events

import android.support.v4.view.ViewPager
import android.util.Log
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class ActivityEvents(val activity: MainActivity){

    fun listen(){
        activity.view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                activity.runOnUiThread {
                    when (position) {
                        0 -> {
                            activity.bottom_navigation.selectedItemId = R.id.profile
                            if (activity.songFragment.childFragmentManager.findFragmentByTag("play_song_fragment") != null) {
                                Log.d("play_song_fragment", "init")
                                activity.songFragment.childFragmentManager.findFragmentByTag("play_song_fragment").onPause()
                            } else {
                                Log.d("play_song_fragment", "null")
                            }
                            if (activity.albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2") != null) {
                                Log.d("play_song_fragment2", "init")
                                activity.albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2").onPause()
                            } else {
                                Log.d("play_song_fragment2", "null")
                            }
                            if (activity.mvFragment.childFragmentManager.findFragmentByTag("play_song_fragment3") != null) {
                                Log.d("play_song_fragment3", "init")
                                activity.mvFragment.childFragmentManager.findFragmentByTag("play_song_fragment3").onPause()
                            } else {
                                Log.d("play_song_fragment3", "null")
                            }
                        }
                        1 -> {
                            activity.bottom_navigation.selectedItemId = R.id.song
                            if (activity.albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2") != null) {
                                Log.d("play_song_fragment2", "init")
                                activity.albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2").onPause()
                            } else {
                                Log.d("play_song_fragment2", "null")
                            }
                            if (activity.mvFragment.childFragmentManager.findFragmentByTag("play_song_fragment3") != null) {
                                Log.d("play_song_fragment3", "init")
                                activity.mvFragment.childFragmentManager.findFragmentByTag("play_song_fragment3").onPause()
                            } else {
                                Log.d("play_song_fragment3", "null")
                            }
                        }
                        2 -> {
                            activity.bottom_navigation.selectedItemId = R.id.album
                            if (activity.songFragment.childFragmentManager.findFragmentByTag("play_song_fragment") != null) {
                                Log.d("play_song_fragment", "init")
                                activity.songFragment.childFragmentManager.findFragmentByTag("play_song_fragment").onPause()
                            } else {
                                Log.d("play_song_fragment", "null")
                            }
                            if (activity.mvFragment.childFragmentManager.findFragmentByTag("play_song_fragment3") != null) {
                                Log.d("play_song_fragment3", "init")
                                activity.mvFragment.childFragmentManager.findFragmentByTag("play_song_fragment3").onPause()
                            } else {
                                Log.d("play_song_fragment3", "null")
                            }
                        }
                        3 -> {
                            activity.bottom_navigation.selectedItemId = R.id.mv
                            if (activity.songFragment.childFragmentManager.findFragmentByTag("play_song_fragment") != null) {
                                Log.d("play_song_fragment", "init")
                                activity.songFragment.childFragmentManager.findFragmentByTag("play_song_fragment").onPause()
                            } else {
                                Log.d("play_song_fragment", "null")
                            }
                            if (activity.albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2") != null) {
                                Log.d("play_song_fragment2", "init")
                                activity.albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2").onPause()
                            } else {
                                Log.d("play_song_fragment2", "null")
                            }
                        }
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