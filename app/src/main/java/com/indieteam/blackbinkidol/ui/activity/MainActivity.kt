package com.indieteam.blackbinkidol.ui.activity

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.ViewpagerAdapter
import com.indieteam.blackbinkidol.ui.events.ActivityEvents
import com.indieteam.blackbinkidol.ui.events.FragmentEvents
import com.indieteam.blackbinkidol.ui.fragment.AlbumFragment
import com.indieteam.blackbinkidol.ui.fragment.MvFragment
import com.indieteam.blackbinkidol.ui.fragment.ProfileFragment
import com.indieteam.blackbinkidol.ui.fragment.SongFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var sX = 0f
    var sY = 0f
    var statusBarHeight = 0
    var navigationBarHeight = 0

    private val profileFragment = ProfileFragment()
    private val songFragment = SongFragment()
    private val albumFragment = AlbumFragment()
    private val mvFragment = MvFragment()

    lateinit var profile: JSONObject
    lateinit var song: JSONObject
    lateinit var album: JSONObject
    lateinit var mv: JSONObject

    lateinit var fragmentEvents: FragmentEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setUI()
        events()
    }

    private fun init(){
        fragmentEvents = FragmentEvents(this)
    }

    private fun events(){
        ActivityEvents(this).listen()
    }

    private fun setUI(){
        getScreen()

        val layout = listOf(profileFragment, songFragment, albumFragment, mvFragment)
        view_pager.adapter = ViewpagerAdapter(supportFragmentManager, layout)
        view_pager.setOffscreenPageLimit(4)

//        view_pager.setOnTouchListener { v, event ->
//            view_pager.currentItem = 0
//            true
//        }
    }

    private fun getScreen(){
        val manager = windowManager.defaultDisplay
        val point  = Point()
        manager.getSize(point)
        sX = point.x/100f
        sY = point.y/100f

        val resources = resources
        val resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val resourcesId2 = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        statusBarHeight = resources.getDimensionPixelSize(resourcesId)
        navigationBarHeight = resources.getDimensionPixelSize(resourcesId2)
    }

    override fun onBackPressed() {
        this.bottom_navigation.visibility = View.VISIBLE
        if(profileFragment.childFragmentManager.backStackEntryCount == 0 && songFragment.childFragmentManager.backStackEntryCount == 0
                && albumFragment.childFragmentManager.backStackEntryCount == 0 && mvFragment.childFragmentManager.backStackEntryCount == 0){
            super.onBackPressed()
        }

        when(view_pager.currentItem){
            0 ->{
                profileFragment.onBack()
            }
            1 ->{
                songFragment.onBack()
            }
            2 ->{
                albumFragment.onBack()
            }
            3 ->{
                mvFragment.onBack()
            }
            else ->{ }
        }
    }
}
