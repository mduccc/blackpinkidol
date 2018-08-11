package com.indieteam.blackbinkidol.ui.activity

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    lateinit var profile: JSONObject
    lateinit var song: JSONObject
    lateinit var album: JSONObject
    lateinit var mv: JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
        init()
        events()
    }

    private fun init(){}

    private fun events(){
        ActivityEvents(this).listen()
    }

    private fun setUI(){
        getScreen()
        val layout = listOf(ProfileFragment(), SongFragment(), AlbumFragment(), MvFragment())
        view_pager.adapter = ViewpagerAdapter(supportFragmentManager, layout)
        view_pager.setOffscreenPageLimit(4)

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

        val resources = resources
        val resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val resourcesId2 = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        statusBarHeight = resources.getDimensionPixelSize(resourcesId)
        navigationBarHeight = resources.getDimensionPixelSize(resourcesId2)
    }
}
