package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.MvListviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.MvData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mv.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Mv{
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(activity: MainActivity){
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
                val mvArr = body.getJSONArray("mv")
                val mv = arrayListOf<MvData>()
                for(item in 0 until mvArr.length()){
                    val key = mvArr.getJSONObject(item).getString("key")
                    val name = mvArr.getJSONObject(item).getString("name")
                    Log.d("Mv", "$key $name")
                    mv.add(MvData(key, name))
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