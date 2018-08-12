package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.AlbumListviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.AlbumData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.AlbumFragment
import com.indieteam.blackbinkidol.ui.update.UpdateUi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_album.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class AlbumHttp(val activity: MainActivity, val fragment: AlbumFragment){
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(){
        dialog = MaterialDialog.Builder(activity)
                .content("Download Data ...\n")
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show()

        val rq = Request.Builder()
                .url(Api().apiAlbum)
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
                activity.album = body
                val albumArr = body.getJSONArray("album")
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
//                activity.runOnUiThread {
//                    activity.album_list_view.adapter = AlbumListviewAdapter(activity, album)
//                    activity.bottom_navigation.measure(0,0)
//                    fragment.album_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
//                }
                UpdateUi().adapterForAlbumListview(activity, fragment, album)
            }

        })
    }
}