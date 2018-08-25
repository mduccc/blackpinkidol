package com.indieteam.blackpinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.process.Song
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_play_song.*

class SongFragment : Fragment() {

    val playSongFragment = PlaySongFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        if(key == null){
            Song(activity as MainActivity, this).get(null)

        }else{
            Song(activity as MainActivity, this).get(key)
        }
        (activity as MainActivity).fragmentEvents.onSongItemsListen(this)
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
