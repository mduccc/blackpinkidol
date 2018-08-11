package com.indieteam.blackbinkidol.process

import android.util.Log
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_singer.*
import kotlinx.android.synthetic.main.fragment_singer.view.*
import org.json.JSONObject
import kotlin.math.sin

class Singer(val activity: MainActivity){

    private lateinit var singer: JSONObject

    fun get(key: String){
        val body = activity.profile
        val memberArr = body.getJSONArray("member")
        for(item in 0 until memberArr.length()){
            if(memberArr.getJSONObject(item).getString("key") == key){
                singer =  JSONObject(memberArr.getJSONObject(item).getString("info"))
                Log.d("singer", singer.toString())
                setUI()
            }
        }
    }

    private fun setUI(){
        Picasso.get()
                .load(singer.getString("image_profile"))
                .resize(200,200)
                .centerCrop()
                .into(activity.avatar_image_singer)
        activity.avatar_image_singer.layoutParams.height = 350
        activity.stage_name.text = "Stage name: " + singer.getString("stage_name")
        activity.full_name.text = "Fullname: " + singer.getString("fullname")
        activity.birthday.text = "Birthday: " + singer.getString("birthday")
        activity.height.text = "Height: " + singer.getString("height")
        activity.nationality.text = "Nationality: " + singer.getString("nationality")
        activity.place_of_birth.text = "Place: " + singer.getString("place_of_birth")
        activity.postion.text = "Postion: " + singer.getString("postion")
        activity.instagram.text = "Instagram: " + singer.getString("instagram")
        activity.group.text = "Group: " + singer.getString("group")
        activity.education.text = if (singer.getString("education") != "null")
            "Education: " + singer.getString("education")
        else
            ""
    }
}