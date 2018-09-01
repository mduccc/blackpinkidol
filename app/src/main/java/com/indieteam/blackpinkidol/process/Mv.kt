package com.indieteam.blackpinkidol.process

import android.util.Log
import com.indieteam.blackpinkidol.model.MvData
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.fragment.MvFragment
import com.indieteam.blackpinkidol.ui.update.UpdateUi

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