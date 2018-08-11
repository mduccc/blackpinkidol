package com.indieteam.blackbinkidol.process

import android.util.Log
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_song.*

class PlaySong(val activity: MainActivity){
    fun play(idVideo: String){
        activity.song_ytb_player_view.initialize({
            it.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    super.onReady()
                    it.loadVideo(idVideo, 0F)
                }
            })
        },true)
    }

    fun lyrics(lyrics: String){
        activity.let {
            it as MainActivity
            it.song_lyrics.text = "Lyrics: \n\n" + lyrics.replace("<br>", "\n").replace("]", "]\n")
            it.bottom_navigation.measure(0,0)
            it.song_ytb_player_view.measure(0,0)
            Log.d("song_ytb_player_view", (((it.sX*100)/16)*9).toString())
            it.song_lyrics_view.layoutParams.height = (it.sY*100 - (((it.sX*100)/16)*9) - it.navigationBarHeight - it.bottom_navigation.measuredHeight + it.statusBarHeight).toInt()
        }
    }
}