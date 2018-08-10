package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.AlbumListviewAdapter
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_album.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Album(){
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(activity: MainActivity){
        dialog = MaterialDialog.Builder(activity)
                .content("Dowload Data ...\n")
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show()

        val rq = Request.Builder()
                .url("http://80.211.52.162:3001/v1/album?name=blackpink")
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
                val albumArr = body.getJSONArray("album")
                val album = arrayListOf<AlbumData>()
                for(item in 0 until albumArr.length()){
                    val name = albumArr.getJSONObject(item).getString("name")
                    val genre = albumArr.getJSONObject(item).getString("genre")
                    val label = albumArr.getJSONObject(item).getString("label")
                    val year = albumArr.getJSONObject(item).getString("year")
                    Log.d("album", "$name $genre $label $year")
                    album.add(AlbumData(name, genre, label, year))
                }
                activity.runOnUiThread {
                    activity.album_list_view.adapter = AlbumListviewAdapter(activity, album)
                    activity.bottom_navigation.measure(0,0)
                    activity.album_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
                }
            }

        })
    }
}