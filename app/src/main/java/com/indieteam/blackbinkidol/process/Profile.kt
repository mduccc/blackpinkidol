package com.indieteam.blackbinkidol.process

import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.GirdviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.AvatarData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.CoverProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Profile{
    private var client = OkHttpClient()
    private lateinit var dialog: MaterialDialog

    fun request(activity: MainActivity){
        dialog = MaterialDialog.Builder(activity)
                .content("Dowload Data ...\n")
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
                    try {
                        it.supportFragmentManager.beginTransaction().add(R.id.rl_profile_fragment, CoverProfileFragment(), "cover_profile")
                                .commit()
                    }catch (e: Exception){}
                    it.runOnUiThread {
                        it.gird_view.let {
                            it.adapter = GirdviewAdapter(activity, member)
                            it.y = activity.sY * 30 + (activity.sY * 5) / 2
                            it.verticalSpacing = ((activity as MainActivity).sX*5).toInt()
                            activity.bottom_navigation.measure(0, 0)
                            it.layoutParams.height = (((activity.sY * 100).toInt() - activity.sY * 30).toInt() - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
                            Log.d("setGirdView","Ok")
                        }
                    }
                }
            }
        })
    }
}