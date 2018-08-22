package com.indieteam.blackbinkidol.ui.activity

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import android.widget.Toast
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.ViewpagerAdapter
import com.indieteam.blackbinkidol.ui.events.ActivityEvents
import com.indieteam.blackbinkidol.ui.events.FragmentEvents
import com.indieteam.blackbinkidol.ui.fragment.AlbumFragment
import com.indieteam.blackbinkidol.ui.fragment.MvFragment
import com.indieteam.blackbinkidol.ui.fragment.ProfileFragment
import com.indieteam.blackbinkidol.ui.fragment.SongFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var sX = 0f
    var sY = 0f
    var statusBarHeight = 0
    var navigationBarHeight = 0

    lateinit var profileFragment: ProfileFragment
    lateinit var songFragment: SongFragment
    lateinit var albumFragment: AlbumFragment
    lateinit var mvFragment: MvFragment

    lateinit var profile: JSONObject
    lateinit var song: JSONObject
    lateinit var album: JSONObject
    lateinit var mv: JSONObject
    private lateinit var layout: ArrayList<Fragment>
    var indexViewPagerNow = 0

    lateinit var fragmentEvents: FragmentEvents
    lateinit var activityEvents: ActivityEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if(savedInstanceState != null){
//            indexViewPagerNow = savedInstanceState.getString("indexViewPagerNow").toInt()
//            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }else{
//            indexViewPagerNow = 0
//            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
//
//        val orientation = getResources().getConfiguration().orientation
//        Log.d("orientation", orientation.toString())
//
//        if(orientation == 1){
//            bottom_navigation.visibility = VISIBLE
//        }else{
//            bottom_navigation.visibility = GONE
//        }

        profile = JSONObject(intent.getStringExtra("profile"))
        song = JSONObject(intent.getStringExtra("song"))
        album = JSONObject(intent.getStringExtra("album"))
        mv = JSONObject(intent.getStringExtra("mv"))

        profileFragment = ProfileFragment()
        songFragment = SongFragment()
        albumFragment = AlbumFragment()
        mvFragment = MvFragment()

        layout = arrayListOf(profileFragment, songFragment, albumFragment, mvFragment)

        setUI()
        events()
    }

    private fun events(){
        activityEvents = ActivityEvents(this)
        activityEvents.listen()
        fragmentEvents = FragmentEvents(this)
    }

    private fun setUI(){
        getScreen()
        view_pager.adapter = ViewpagerAdapter(supportFragmentManager, layout)
        view_pager.currentItem = indexViewPagerNow
        view_pager.offscreenPageLimit = 4
    }

    private fun getScreen(){
        val manager = windowManager.defaultDisplay
        val point = Point()
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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("indexViewPagerNow", view_pager.currentItem.toString())
    }
}
