package com.indieteam.blackbinkidol

import android.R.attr.extractNativeLibs
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.attr.fragment
import android.util.Log
import com.flipkart.youtubeview.YouTubePlayerView
import com.flipkart.youtubeview.activity.YouTubeActivity
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_player.*


class PlayerActivity: YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        ytb_player_view.initialize("AIzaSyAHjMKVToFXdnq6wdt6rjYCnJ1lQ3c3AMc", object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                p1?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                p1?.loadVideo("xLCn88bfW1o")
                Log.d("load", "ok")
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Log.d("load", "false")
            }
        })
    }
}
