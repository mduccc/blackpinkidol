package com.indieteam.blackbinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.process.Album
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.events.FragmentEvents
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_song.*

class AlbumFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmentcd ..

        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Album(activity as MainActivity, this).request()
        (activity as MainActivity).fragmentEvents.onAlbumItemsListen(this)
    }

    fun onBack(): Int {
        val count = childFragmentManager.backStackEntryCount
        if (count > 0) {
            childFragmentManager.popBackStackImmediate()
        }
        return count
    }
}
