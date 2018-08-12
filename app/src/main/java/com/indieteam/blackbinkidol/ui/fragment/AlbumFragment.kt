package com.indieteam.blackbinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.process.AlbumHttp
import com.indieteam.blackbinkidol.ui.activity.MainActivity

class AlbumFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmentcd ..

        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AlbumHttp(activity as MainActivity, this).request()
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
