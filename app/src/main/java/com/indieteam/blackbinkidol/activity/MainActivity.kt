package com.indieteam.blackbinkidol.activity

import android.graphics.Point
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.GirdviewAdapter
import com.indieteam.blackbinkidol.adapter.ViewpagerAdapter
import com.indieteam.blackbinkidol.fragment.AlbumFragment
import com.indieteam.blackbinkidol.fragment.VideoFragment
import com.indieteam.blackbinkidol.fragment.ProfileFragment
import com.indieteam.blackbinkidol.fragment.SongFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity() {

    var sX = 0f
    var sY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
    }

    private fun setUI(){
        getScreen()
        val layout = listOf(ProfileFragment(), SongFragment(), AlbumFragment(), VideoFragment())
        view_pager.adapter = ViewpagerAdapter(supportFragmentManager, layout)
        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.profile ->{
                    view_pager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.song ->{
                    view_pager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.album ->{
                    view_pager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.mv ->{
                    view_pager.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener true
            }
        }

        //disable swipe
        view_pager.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })
    }

    private fun getScreen(){
        val manager = windowManager.defaultDisplay
        val point  = Point()
        manager.getSize(point)
        sX = point.x/100f
        sY = point.y/100f
    }
}
