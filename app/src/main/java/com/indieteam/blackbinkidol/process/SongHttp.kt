package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.SongListviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.SongData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.SongFragment
import com.indieteam.blackbinkidol.ui.update.UpdateUi
import kotlinx.android.synthetic.main.fragment_song.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SongHttp(val activity: MainActivity, val fragment: SongFragment){
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(keyAlbum: String?){
        if(keyAlbum == null){
            getAll()
        }else{
            getInAlbum(keyAlbum)
        }
    }

    private fun getInAlbum(keyAlbum: String){
        Log.d("okok", keyAlbum)
        val albumJson = activity.album
        val songJson = activity.song
        val albumArr = albumJson.getJSONArray("album")
        val result = arrayListOf<SongData>()
        for(item in 0 until albumArr.length()){
            if(albumArr.getJSONObject(item).getString("key") == keyAlbum){
                val songArr = albumArr.getJSONObject(item).getJSONArray("song")
                for(itemChil in 0 until songArr.length()){
                    val keySong = songArr.getJSONObject(itemChil).get("key")
                    val songNewArr = songJson.getJSONArray("song")
                    for(itemChil2 in 0 until songNewArr.length()){
                        if(songNewArr.getJSONObject(itemChil2).getString("key") == keySong){
                            val key = songNewArr.getJSONObject(itemChil2).getString("key")
                            val name = songNewArr.getJSONObject(itemChil2).getString("name")
                            val info = "Blackpink | Album: " + songNewArr.getJSONObject(itemChil2).getString("album")
                            val year = songNewArr.getJSONObject(itemChil2).getString("year")
                            Log.d("data", "$key $name $info $year")
                            result.add(SongData(key, name, info, year, R.id.rl_album_fragment.toString()))
                            activity.runOnUiThread {
                                fragment.song_list_view.adapter = SongListviewAdapter(activity, result)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getAll(){
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
                    song.add(SongData(key, name, info, year, R.id.rl_song_fragment.toString()))
                    Log.d("song", "$key $name $info $year")
                }

//                activity.runOnUiThread {
//                    fragment.song_list_view.adapter = SongListviewAdapter(activity, song)
//                    activity.bottom_navigation.measure(0,0)
//                    fragment.song_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
//                }
            UpdateUi().adapterForSongListView(activity, fragment, song)
            }

        })
    }
}