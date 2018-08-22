package com.indieteam.blackpinkidol.process

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.indieteam.blackpinkidol.api.Api
import com.indieteam.blackpinkidol.model.AlbumData
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.activity.SplashActivity
import com.indieteam.blackpinkidol.ui.update.UpdateUi
import es.dmoral.toasty.Toasty
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class GetAllDataHttp(val activity: SplashActivity){
    private var client = OkHttpClient()
    private var profileCallbackCode = 0
    private var songCallbackcode = 0
    private var albumCallbackCode  = 0
    private var mvCallbackCode = 0
    private lateinit var profile: JSONObject
    private lateinit var song: JSONObject
    private lateinit var album: JSONObject
    private lateinit var mv: JSONObject

    fun get(){
        callbackResult()
        getProfile()
        getSong()
        getAlbum()
        getMv()

    }

    fun getProfile(){
        val rq = Request.Builder()
                .url(Api().apiProfile)
                .build()

        client.newCall(rq).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread {
                    Toasty.warning(activity, "No Network", Toast.LENGTH_SHORT, true).show();
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = JSONObject(response?.body()?.string())
                profile = body
                profileCallbackCode = 1
            }
        })
    }

    fun getSong(){
        val rq = Request.Builder()
                .url(Api().apiSong)
                .build()

        client.newCall(rq).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread {
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = JSONObject(response?.body()?.string())
                song = body
                songCallbackcode = 1
            }
        })
    }

    fun getAlbum(){
        val rq = Request.Builder()
                .url(Api().apiAlbum)
                .build()

        client.newCall(rq).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread {
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = JSONObject(response?.body()?.string())
                album = body
                albumCallbackCode = 1
            }
        })
    }

    fun getMv(){
        val rq = Request.Builder()
                .url(Api().apiMv)
                .build()

        client.newCall(rq).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                activity.runOnUiThread {
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = JSONObject(response?.body()?.string())
                mv = body
                mvCallbackCode = 1
            }
        })
    }

    fun callbackResult(){
        Timer().scheduleAtFixedRate(0, 500){
            if(profileCallbackCode == 1 &&
                    songCallbackcode ==1 &&
                    albumCallbackCode == 1 &&
                    mvCallbackCode == 1){
                this.cancel()
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("profile", profile.toString())
                intent.putExtra("song", song.toString())
                intent.putExtra("album", album.toString())
                intent.putExtra("mv", mv.toString())
                activity.startActivity(intent)
                activity.finish()
            }
        }
    }
}