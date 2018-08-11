package com.indieteam.blackbinkidol.process

import android.util.Log
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.SingerFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_singer.*
import org.json.JSONObject

class Singer(val activity: MainActivity, val fragment: SingerFragment){

    private lateinit var singer: JSONObject

    fun get(key: String){
        val body = activity.profile
        val memberArr = body.getJSONArray("member")
        for(item in 0 until memberArr.length()){
            if(memberArr.getJSONObject(item).getString("key") == key){
                singer =  JSONObject(memberArr.getJSONObject(item).getString("info"))
                setUI()
            }
        }
    }

    private fun setUI(){
        fragment.let {
            Picasso.get()
                    .load(singer.getString("image_profile"))
                    .resize(200, 200)
                    .centerCrop()
                    .into(it.avatar_image_singer)
            it.avatar_image_singer.layoutParams.height = 350
            it.stage_name.text = "Stage name: " + singer.getString("stage_name")
            it.full_name.text = "Fullname: " + singer.getString("fullname")
            it.birthday.text = "Birthday: " + singer.getString("birthday")
            it.height.text = "Height: " + singer.getString("height")
            it.nationality.text = "Nationality: " + singer.getString("nationality")
            it.place_of_birth.text = "Place: " + singer.getString("place_of_birth")
            it.postion.text = "Postion: " + singer.getString("postion")
            it.instagram.text = "Instagram: " + singer.getString("instagram")
            it.group.text = "Group: " + singer.getString("group")
            it.education.text = if (singer.getString("education") != "null")
                "Education: " + singer.getString("education")
            else
                ""
            Log.d("singer", singer.toString())
        }
    }
}