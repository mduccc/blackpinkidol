package com.indieteam.blackpinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.adapter.SongListviewAdapter
import com.indieteam.blackpinkidol.api.Api
import com.indieteam.blackpinkidol.model.SongData
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.fragment.SongFragment
import com.indieteam.blackpinkidol.ui.update.UpdateUi
import kotlinx.android.synthetic.main.fragment_song.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Song(val activity: MainActivity, val fragment: SongFragment){

    fun get(keyAlbum: String?){
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
        // find to album
        for(item in 0 until albumArr.length()){
            if(albumArr.getJSONObject(item).getString("key") == keyAlbum){
                val songArr = albumArr.getJSONObject(item).getJSONArray("song")
                // find all song in album
                for(itemChil in 0 until songArr.length()){
                    val keySong = songArr.getJSONObject(itemChil).get("key")
                    val songNewArr = songJson.getJSONArray("song")
                    //find info song
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
        val songArr = activity.song.getJSONArray("song")
        val song = arrayListOf<SongData>()
        for(item in 0 until songArr.length()){
            val key = songArr.getJSONObject(item).getString("key")
            val name = songArr.getJSONObject(item).getString("name")
            val info = "Blackpink | Album: " + songArr.getJSONObject(item).getString("album")
            val year = songArr.getJSONObject(item).getString("year")
            song.add(SongData(key, name, info, year, R.id.rl_song_fragment.toString()))
            Log.d("song", "$key $name $info $year")
        }
        UpdateUi().adapterForSongListView(activity, fragment, song)
    }
}