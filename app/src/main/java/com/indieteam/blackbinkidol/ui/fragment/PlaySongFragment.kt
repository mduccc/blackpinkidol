package com.indieteam.blackbinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.process.PlaySong
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import org.json.JSONObject

class PlaySongFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        activity?.let{
            it as MainActivity
            val body = it.song
            val songArr = body.getJSONArray("song")
            for(item in 0 until songArr.length()){
                if(songArr.getJSONObject(item).getString("key") == key){
                    val idVideo = songArr.getJSONObject(item).getString("youtube")
                    val lyrics = JSONObject(songArr.getJSONObject(item).getString("lyrics")).getString("romanization")
                    val player = PlaySong(it, this)
                    player.play(idVideo)
                    player.lyrics(lyrics)
                }
            }
        }
    }

}
