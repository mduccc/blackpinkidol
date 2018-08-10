package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.GirdviewAdapter
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.CoverProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Profile{
    private var client = OkHttpClient()

    fun request(activity: MainActivity){
        val rq = Request.Builder()
                .url("http://80.211.52.162:3001/v1/profile?name=blackpink")
                .build()

        client.newCall(rq).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread {
                    Toast.makeText(activity, "errr", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = JSONObject(response?.body()?.string())
                val memberArr = body.getJSONArray("member")
                val member = arrayListOf<AvatarData>()
                for(item in 0 until  memberArr.length()){
                    val memberObj = JSONObject(memberArr.getJSONObject(item).getString("info"))
                    val name = memberObj.getString("stage_name")
                    val imageProfile = memberObj.getString("image_profile")
                    Log.d("member","$name $imageProfile")
                    member.add(AvatarData(name, imageProfile))
                }
                activity.let { it ->
                    it.supportFragmentManager.beginTransaction().add(R.id.rl_profile_fragment, CoverProfileFragment(), "cover_profile")
                            .commit()
                    it.runOnUiThread {
                        it.gird_view.let {
                            it.adapter = GirdviewAdapter(activity, member)
                            it.y = activity.sY * 30 + (activity.sY * 5) / 2
                            //it.verticalSpacing = ((activity as MainActivity).sX*5).toInt()
                            activity.bottom_navigation.measure(0, 0)
                            it.layoutParams.height = (((activity.sY * 100).toInt() - activity.sY * 30).toInt() - activity.bottom_navigation.measuredHeight - Math.round(((activity).sY * 10 / 2).toDouble())).toInt()
                        }
                    }
                }
            }
        })
    }
}