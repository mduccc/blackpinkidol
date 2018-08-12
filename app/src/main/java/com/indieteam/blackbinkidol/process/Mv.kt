package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.MvGirdviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.MvData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.MvFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mv.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Mv(val activity: MainActivity, val fragment: MvFragment){
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(){
        dialog = MaterialDialog.Builder(activity)
                .content("Download Data ...\n")
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show()

        val rq = Request.Builder()
                .url(Api().apiMv)
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
                activity.mv = body
                val mvArr = body.getJSONArray("mv")
                val mv = arrayListOf<MvData>()
                for(item in 0 until mvArr.length()){
                    val key = mvArr.getJSONObject(item).getString("key")
                    val idVideo = mvArr.getJSONObject(item).getString("youtube")
                    mv.add(MvData(idVideo, key))
                }
                activity.runOnUiThread {
                    fragment.mv_gird_view.adapter = MvGirdviewAdapter(activity, mv)
                    activity.bottom_navigation.measure(0,0)
                    fragment.mv_gird_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
                }
            }

        })
    }
}