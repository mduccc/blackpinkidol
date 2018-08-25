package com.indieteam.blackpinkidol.ui.fragment

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.process.PlaySong
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_song.*
import org.json.JSONObject

class PlaySongFragment : Fragment() {

    private lateinit var player: PlaySong
    var fullScreen = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("key")
        var videoId = arguments?.getString("videoId")
        activity?.let{
            it as MainActivity
            if(key != "null") {
                val body = it.song
                val songArr = body.getJSONArray("song")
                for (item in 0 until songArr.length()) {
                    if (key != "null" && songArr.getJSONObject(item).getString("key") == key) {
                        videoId = songArr.getJSONObject(item).getString("youtube")
                        val lyrics = JSONObject(songArr.getJSONObject(item).getString("lyrics")).getString("romanization")
                        player = PlaySong(it, this)
                        player.play(videoId!!)
                        player.lyrics(lyrics)
                    }
                }
            }else{
                player = PlaySong(it, this)
                player.play(videoId!!)
            }
        }
        song_ytb_player_view.addFullScreenListener(object : YouTubePlayerFullScreenListener{
            override fun onYouTubePlayerEnterFullScreen() {
                (activity as MainActivity).bottom_navigation.visibility = GONE
                //Toast.makeText(activity, "FullScreen", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                (activity as MainActivity).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
                (activity as MainActivity).activityEvents.disableSwipe()
                fullScreen = 1
            }

            override fun onYouTubePlayerExitFullScreen() {
                (activity as MainActivity).bottom_navigation.visibility = VISIBLE
                //Toast.makeText(activity, "Exit FullScreen", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                (activity as MainActivity).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                (activity as MainActivity).activityEvents.enableSwipe()
                fullScreen = 0
            }
        })
    }

    override fun onPause() {
        super.onPause()
        try {
            Log.d("status_play", "pause")
            player.ytbPlayerView?.pause()
        }catch (e: Exception){
            Log.d("status_play", "cannot pause")
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            Log.d("status_play", "stop")
            player.ytbPlayerView = null
            song_ytb_player_view.release()
        }catch (e: Exception){
            Log.d("status_play", "cannot stop")
        }
    }

}
