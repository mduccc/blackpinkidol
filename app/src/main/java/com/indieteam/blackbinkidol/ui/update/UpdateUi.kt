package com.indieteam.blackbinkidol.ui.update

import android.util.Log
import android.view.View
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.adapter.AlbumListviewAdapter
import com.indieteam.blackbinkidol.adapter.GirdviewAdapter
import com.indieteam.blackbinkidol.adapter.MvGirdviewAdapter
import com.indieteam.blackbinkidol.adapter.SongListviewAdapter
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.AlbumData
import com.indieteam.blackbinkidol.model.AvatarData
import com.indieteam.blackbinkidol.model.MvData
import com.indieteam.blackbinkidol.model.SongData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_album.view.*
import kotlinx.android.synthetic.main.layout_avatar.view.*
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_mv.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_singer.*
import kotlinx.android.synthetic.main.fragment_song.*
import kotlinx.android.synthetic.main.layout_mv.view.*
import kotlinx.android.synthetic.main.layout_song.view.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class UpdateUi{

    fun layoutAlbum(view: View, data: AlbumData){
        view.album_key.text = data.key
        view.album_name.text = data.name
        view.album_genre.text = "Genre: "+ data.genre
        view.album_label.text = "Lable: "+ data.label
        view.album_year.text = "Year: "+ data.year
    }

    fun layoutAvatar(view: View, data: AvatarData){
        view.avatar_key.text = data.key
        view.avatar_image.layoutParams.height = 350
        view.avatar_name.text = data.name

        Picasso.get()
                .load(data.imageProfile)
                .resize(200,200)
                .centerCrop()
                .into(view.avatar_image)
    }

    fun layoutMv(activity: MainActivity, client: OkHttpClient, view: View, data: MvData){
        view.mv_key.text = data.key
        view.mv_videoId.text = data.idVideo

        Picasso.get()
                .load(Api().youtubeThumbnails(data.idVideo))
                .into(view.mv_thumbnail)
        val rq = Request.Builder()
                .url(Api().youtubeTitle(data.idVideo))
                .build()
        client.newCall(rq).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {}

            override fun onResponse(call: Call?, response: Response?) {
                val body = JSONObject(response?.body()?.string())
                val title = body.getString("title")
                activity.runOnUiThread {
                    view.mv_title.text = title
                }
            }
        })
    }

    fun layoutSong(view: View, data: SongData){
        view.song_key.text = data.key
        view.song_name.text = data.name
        view.song_group.text = "Singer: "+ data.info
        view.song_year.text = "Year: "+ data.year
        view.song_id_root_view.text = data.rootView
    }

    fun adapterForAlbumListview(activity: MainActivity, fragment: AlbumFragment, album: ArrayList<AlbumData>){
        activity.runOnUiThread {
            activity.album_list_view.adapter = AlbumListviewAdapter(activity, album)
            activity.bottom_navigation.measure(0,0)
            fragment.album_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
        }
    }

    fun adapterForMvGirdview(activity: MainActivity, fragment: MvFragment, mv: ArrayList<MvData>){
        activity.runOnUiThread {
            fragment.mv_gird_view.adapter = MvGirdviewAdapter(activity, mv)
            activity.bottom_navigation.measure(0,0)
            fragment.mv_gird_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
        }
    }

    fun adapterForProfileGirdview(activity: MainActivity, fragment: ProfileFragment, member: ArrayList<AvatarData>){
        activity.let { it ->
            try {
                it.supportFragmentManager.beginTransaction().add(R.id.rl_profile_fragment, CoverProfileFragment(), "cover_profile")
                        .commit()
            }catch (e: Exception){}
            it.runOnUiThread {
                fragment.gird_view.let {
                    it.adapter = GirdviewAdapter(activity, member)
                    fragment.rl_profile_fragment.measure(0,0)
                    it.y = fragment.rl_profile_fragment.measuredHeight.toFloat()
                    it.verticalSpacing = (activity.sX*5).toInt()
                    activity.bottom_navigation.measure(0, 0)
                    it.layoutParams.height = (((activity.sY * 100).toInt() - fragment.rl_profile_fragment.measuredHeight) - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight)
                    Log.d("setGirdView","Ok")
                }
            }
        }
    }

    fun layoutSinger(fragment: SingerFragment, singer: JSONObject){
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

    fun adapterForSongListView(activity: MainActivity, fragment: SongFragment, song: ArrayList<SongData>){
        activity.runOnUiThread {
            fragment.song_list_view.adapter = SongListviewAdapter(activity, song)
            activity.bottom_navigation.measure(0,0)
            fragment.song_list_view.layoutParams.height = (activity.sY*100 - activity.bottom_navigation.measuredHeight - activity.navigationBarHeight + activity.statusBarHeight).toInt()
        }
    }
}