package com.indieteam.blackbinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.process.Song
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.events.FragmentEvents
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_song.*

class SongFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        if(key == null){
            Song(activity as MainActivity, this).request(null)

        }else{
            Song(activity as MainActivity, this).request(key)
        }
        (activity as MainActivity).fragmentEvents.onSongItemsListen(this)
    }

    fun onBack(): Int {
        val count = childFragmentManager.backStackEntryCount
        if (count > 0) {
            childFragmentManager.popBackStackImmediate()
        }
        return count
    }
}
