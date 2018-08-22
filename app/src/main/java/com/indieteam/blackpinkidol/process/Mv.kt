package com.indieteam.blackpinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackpinkidol.adapter.MvGirdviewAdapter
import com.indieteam.blackpinkidol.api.Api
import com.indieteam.blackpinkidol.model.MvData
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.fragment.MvFragment
import com.indieteam.blackpinkidol.ui.update.UpdateUi
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