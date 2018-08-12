package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.AvatarData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.ProfileFragment
import com.indieteam.blackbinkidol.ui.update.UpdateUi
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ProfileHttp(val activity: MainActivity, val fragment: ProfileFragment){
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(){
        dialog = MaterialDialog.Builder(activity)
                .content("Download Data ...\n")
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show()

        val rq = Request.Builder()
                .url(Api().apiProfile)
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
                activity.profile = body
                val memberArr = body.getJSONArray("member")
                val member = arrayListOf<AvatarData>()
                for(item in 0 until  memberArr.length()){
                    val key = memberArr.getJSONObject(item).getString("key")
                    val memberObj = JSONObject(memberArr.getJSONObject(item).getString("info"))
                    val name = memberObj.getString("stage_name")
                    val imageProfile = memberObj.getString("image_profile")
                    Log.d("member","$key $name $imageProfile")
                    member.add(AvatarData(key, name, imageProfile))
                }

//                activity.let { it ->
//                    try {
//                        it.supportFragmentManager.beginTransaction().add(R.id.rl_profile_fragment, CoverProfileFragment(), "cover_profile")
//                                .commit()
//                    }catch (e: Exception){}
//                    it.runOnUiThread {
//                        fragment.gird_view.let {
//                            it.adapter = GirdviewAdapter(activity, member)
//                            fragment.rl_profile_fragment.measure(0,0)
//                            it.y = fragment.rl_profile_fragment.measuredHeight.toFloat()
//                            it.verticalSpacing = (activity.sX*5).toInt()
//                            activity.bottom_navigation.measure(0, 0)
//                            it.layoutParams.height = (((activity.sY * 100).toInt() - fragment.rl_profile_fragment.measuredHeight) - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight)
//                            Log.d("setGirdView","Ok")
//                        }
//                    }
//                }
                UpdateUi().adapterForProfileGirdview(activity, fragment, member)
            }
        })
    }
}