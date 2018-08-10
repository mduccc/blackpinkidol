package com.indieteam.blackbinkidol.ui.activity

import android.graphics.Point
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.ViewpagerAdapter
import com.indieteam.blackbinkidol.ui.fragment.AlbumFragment
import com.indieteam.blackbinkidol.ui.fragment.ProfileFragment
import com.indieteam.blackbinkidol.ui.fragment.SongFragment
import com.indieteam.blackbinkidol.ui.fragment.VideoFragment
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var sX = 0f
    var sY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
        init()
    }

    private fun init(){
    }

    private fun setUI(){
        getScreen()
        val layout = listOf(ProfileFragment(), SongFragment(), AlbumFragment(), VideoFragment())
        view_pager.adapter = ViewpagerAdapter(supportFragmentManager, layout)
        view_pager.setOffscreenPageLimit(4)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> bottom_navigation.selectedItemId = R.id.profile
                    1 -> bottom_navigation.selectedItemId = R.id.song
                    2 -> bottom_navigation.selectedItemId = R.id.album
                    3 -> bottom_navigation.selectedItemId = R.id.mv
                    else ->{}
                }
            }

        })
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
//        view_pager.setOnTouchListener(object: View.OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                return true
//            }
//        })
    }

    private fun getScreen(){
        val manager = windowManager.defaultDisplay
        val point  = Point()
        manager.getSize(point)
        sX = point.x/100f
        sY = point.y/100f
    }
}
