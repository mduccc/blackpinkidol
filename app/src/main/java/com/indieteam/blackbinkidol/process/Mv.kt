package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.adapter.MvGirdviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.MvData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.MvFragment
import com.indieteam.blackbinkidol.ui.update.UpdateUi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mv.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Mv(val activity: MainActivity, val fragment: MvFragment){

    fun get(){
        val mvArr = activity.mv.getJSONArray("mv")
        val mv = arrayListOf<MvData>()
        for(item in 0 until mvArr.length()){
            var key: String
            if(mvArr.getJSONObject(item).getString("key") != null){
                key = mvArr.getJSONObject(item).getString("key")
            }else{
                key = "null"
            }
            Log.d("keySong", mvArr.getJSONObject(item).getString("key"))
            val idVideo = mvArr.getJSONObject(item).getString("youtube")
            mv.add(MvData(idVideo, key))
        }
        UpdateUi().adapterForMvGirdview(activity, fragment, mv)

    }
}