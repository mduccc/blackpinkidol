package com.indieteam.blackpinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackpinkidol.api.Api
import com.indieteam.blackpinkidol.model.AvatarData
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.fragment.ProfileFragment
import com.indieteam.blackpinkidol.ui.update.UpdateUi
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Profile(val activity: MainActivity, val fragment: ProfileFragment){
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun get(){
        val memberArr = activity.profile.getJSONArray("member")
        val member = arrayListOf<AvatarData>()
        for(item in 0 until  memberArr.length()){
            val key = memberArr.getJSONObject(item).getString("key")
            val memberObj = JSONObject(memberArr.getJSONObject(item).getString("info"))
            val name = memberObj.getString("stage_name")
            val imageProfile = memberObj.getString("image_profile")
            Log.d("member","$key $name $imageProfile")
            member.add(AvatarData(key, name, imageProfile))
        }
        UpdateUi().adapterForProfileGirdview(activity, fragment, member)
    }
}