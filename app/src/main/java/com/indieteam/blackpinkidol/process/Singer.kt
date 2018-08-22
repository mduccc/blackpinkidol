package com.indieteam.blackpinkidol.process

import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.fragment.SingerFragment
import com.indieteam.blackpinkidol.ui.update.UpdateUi
import org.json.JSONObject

class Singer(val activity: MainActivity, val fragment: SingerFragment){

    private lateinit var singer: JSONObject

    fun get(key: String){
        val body = activity.profile
        val memberArr = body.getJSONArray("member")
        for(item in 0 until memberArr.length()){
            if(memberArr.getJSONObject(item).getString("key") == key){
                singer =  JSONObject(memberArr.getJSONObject(item).getString("info"))
                UpdateUi().layoutSinger(fragment, singer)
            }
        }
    }
}