package com.indieteam.blackbinkidol.ui.events

import android.opengl.Visibility
import android.os.Bundle
import android.view.View.GONE
import android.widget.RelativeLayout
import android.widget.Toast
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.SingerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.album_layout.view.*
import kotlinx.android.synthetic.main.avatar_layout.view.*
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_mv.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_song.*
import kotlinx.android.synthetic.main.mv_layout.view.*
import kotlinx.android.synthetic.main.song_layout.view.*

class FragmentEvents(val activity: MainActivity){

    private val singerFragment = SingerFragment()
    fun onProfileItemsListen() {
        activity.gird_view.setOnItemClickListener { parent, view, position, id ->
            val key = view.avatar_key.text
            activity.runOnUiThread {
                if(!singerFragment.isAdded) {
                    val bundle = Bundle()
                    bundle.putString("key", key as String)
                    singerFragment.arguments = bundle
                    activity.supportFragmentManager.beginTransaction().add(R.id.rl_profile_fragment, singerFragment, "singer_fragment")
                            .addToBackStack("singer_fragment")
                            .commit()
                }
            }
        }
    }

    fun onSongItemsListen() {
        activity.song_list_view.setOnItemClickListener { parent, view, position, id ->
            activity.runOnUiThread {
                Toast.makeText(activity, view.song_key.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onAlbumItemsListen(){
        activity.album_list_view.setOnItemClickListener { parent, view, position, id ->
            activity.runOnUiThread {
                Toast.makeText(activity, view.album_key.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onMvItemsListen(){
        activity.mv_list_view.setOnItemClickListener { parent, view, position, id ->
            activity.runOnUiThread {
                Toast.makeText(activity, view.mv_key.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

}