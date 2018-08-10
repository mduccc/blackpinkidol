package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.SongListviewAdapter
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_song.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Song{
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(activity: MainActivity){

        dialog = MaterialDialog.Builder(activity)
                .content("Dowload Data ...\n")
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show()

        val rq = Request.Builder()
                .url("http://80.211.52.162:3001/v1/song?name=blackpink")
                .build()

        client.newCall(rq).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread {
                    dialog.cancel()
                    Toast.makeText(activity, "Error network", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                dialog.cancel()
                val body = JSONObject(response?.body()?.string())
                val songArr = body.getJSONArray("song")
                val song = arrayListOf<SongData>()
                for(item in 0 until songArr.length()){
                    val name = songArr.getJSONObject(item).getString("name")
                    val info = "Blackpink | Album: " + songArr.getJSONObject(item).getString("album")
                    val year = songArr.getJSONObject(item).getString("year")
                    song.add(SongData(name, info, year))
                    Log.d("song", "$name $info $year")
                }

                activity.runOnUiThread {
                    activity.song_list_view.adapter = SongListviewAdapter(activity, song)
                    activity.bottom_navigation.measure(0,0)
                    activity.song_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
                }
            }

        })
    }
}