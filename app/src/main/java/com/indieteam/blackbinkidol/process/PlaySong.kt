package com.indieteam.blackbinkidol.process

import android.util.Log
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.PlaySongFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_song.*

class PlaySong(val activity: MainActivity, val fragment: PlaySongFragment){

    var ytbPlayerView: YouTubePlayer? = null

    fun play(idVideo: String){
        fragment.song_ytb_player_view.initialize({
            it.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    super.onReady()
                    it.loadVideo(idVideo, 0F)
                    ytbPlayerView = it
                }
            })
        },true)
    }

    fun lyrics(lyrics: String){
        fragment.let {
            it.song_lyrics.text = "Lyrics: \n\n" + lyrics.replace("<br>", "\n").replace("]", "]\n")
            activity.bottom_navigation.measure(0,0)
            fragment.song_ytb_player_view.measure(0,0)
            Log.d("song_ytb_player_view", (((activity.sX*100)/16)*9).toString())
            fragment.song_lyrics_view.layoutParams.height = (activity.sY*100 - (((activity.sX*100)/16)*9) - activity.navigationBarHeight - activity.bottom_navigation.measuredHeight + activity.statusBarHeight).toInt()
        }
    }
}