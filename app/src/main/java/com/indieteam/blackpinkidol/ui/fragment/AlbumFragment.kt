package com.indieteam.blackpinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.process.Album
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_play_song.*

class AlbumFragment : Fragment() {

    val songFragment = SongFragment()
    val playSongFragment = PlaySongFragment()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragmentcd ..

        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Album(activity as MainActivity, this).get()
        (activity as MainActivity).fragmentEvents.onAlbumItemsListen(this)
    }

    fun onBack(): Int {
        val count = childFragmentManager.backStackEntryCount

        if (playSongFragment.fullScreen == 1)
            playSongFragment.song_ytb_player_view.exitFullScreen()
        else
            if (count > 0) {
                childFragmentManager.popBackStackImmediate()
            }

        return count
    }
}
