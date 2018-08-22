package com.indieteam.blackpinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackpinkidol.api.Api
import com.indieteam.blackpinkidol.model.AlbumData
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.fragment.AlbumFragment
import com.indieteam.blackpinkidol.ui.update.UpdateUi
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