package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.AlbumData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.AlbumFragment
import com.indieteam.blackbinkidol.ui.update.UpdateUi
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Album(val activity: MainActivity, val fragment: AlbumFragment){

    fun get(){
        val albumArr = activity.album.getJSONArray("album")
        val album = arrayListOf<AlbumData>()
        for(item in 0 until albumArr.length()){
            val key = albumArr.getJSONObject(item).getString("key")
            val name = albumArr.getJSONObject(item).getString("name")
            val genre = albumArr.getJSONObject(item).getString("genre")
            val label = albumArr.getJSONObject(item).getString("label")
            val year = albumArr.getJSONObject(item).getString("year")
            Log.d("album", "$key $name $genre $label $year")
            album.add(AlbumData(key, name, genre, label, year))
        }
        UpdateUi().adapterForAlbumListview(activity, fragment, album)
    }
}