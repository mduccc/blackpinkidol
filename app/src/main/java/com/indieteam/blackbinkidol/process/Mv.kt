package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.AlbumListviewAdapter
import com.indieteam.blackbinkidol.adapter.MvListviewAdapter
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_mv.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Mv{
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(activity: MainActivity){
        dialog = MaterialDialog.Builder(activity)
                .content("Dowload Data ...\n")
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show()

        val rq = Request.Builder()
                .url("http://80.211.52.162:3001/v1/mv?name=blackpink")
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
                val mvArr = body.getJSONArray("mv")
                val mv = arrayListOf<MvData>()
                for(item in 0 until mvArr.length()){
                    val name = mvArr.getJSONObject(item).getString("name")
                    Log.d("Mv", "$name")
                    mv.add(MvData(name))
                }
                activity.runOnUiThread {
                    activity.mv_list_view.adapter = MvListviewAdapter(activity, mv)
                    activity.bottom_navigation.measure(0,0)
                    activity.mv_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
                }
            }

        })
    }
}