package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.SongListviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.SongData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.SongFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_song.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Song(val activity: MainActivity, val fragment: SongFragment){
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(){

        dialog = MaterialDialog.Builder(activity)
                .content("Download Data ...\n")
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show()

        val rq = Request.Builder()
                .url(Api().apiSong)
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
                activity.song = body
                val songArr = body.getJSONArray("song")
                val song = arrayListOf<SongData>()
                for(item in 0 until songArr.length()){
                    val key = songArr.getJSONObject(item).getString("key")
                    val name = songArr.getJSONObject(item).getString("name")
                    val info = "Blackpink | Album: " + songArr.getJSONObject(item).getString("album")
                    val year = songArr.getJSONObject(item).getString("year")
                    song.add(SongData(key, name, info, year))
                    Log.d("song", "$key $name $info $year")
                }

                activity.runOnUiThread {
                    activity.song_list_view.adapter = SongListviewAdapter(activity, song)
                    activity.bottom_navigation.measure(0,0)
                    fragment.song_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
                }
            }

        })
    }
}