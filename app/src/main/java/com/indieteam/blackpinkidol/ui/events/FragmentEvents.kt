package com.indieteam.blackpinkidol.ui.events

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.ui.activity.MainActivity
import com.indieteam.blackpinkidol.ui.fragment.*
import kotlinx.android.synthetic.main.layout_album.view.*
import kotlinx.android.synthetic.main.layout_avatar.view.*
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_mv.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_song.*
import kotlinx.android.synthetic.main.layout_mv.view.*
import kotlinx.android.synthetic.main.layout_song.view.*

class FragmentEvents(val activity: MainActivity){

    private val singerFragment = SingerFragment()
    private lateinit var albumFragment: AlbumFragment
    private lateinit var mvFragment: MvFragment

    fun onProfileItemsListen(fragment: ProfileFragment) {
        fragment.gird_view.setOnItemClickListener { parent, view, position, id ->
            val key = view.avatar_key.text
            activity.runOnUiThread {
                val bundle = Bundle()
                bundle.putString("key", key as String)
                singerFragment.arguments = bundle
                if(fragment.getChildFragmentManager().findFragmentByTag("singer_fragment") != null) {
                    fragment.getChildFragmentManager().beginTransaction().replace(R.id.rl_profile_fragment, singerFragment, "singer_fragment")
                            .addToBackStack(null)
                            .commit()
                }else{
                    fragment.getChildFragmentManager().beginTransaction().add(R.id.rl_profile_fragment, singerFragment, "singer_fragment")
                            .addToBackStack(null)
                            .commit()
                }
            }
        }
    }

    fun onSongItemsListen(fragment: SongFragment) {
        fragment.song_list_view.setOnItemClickListener { parent, view, position, id ->
            activity.runOnUiThread {
                val key = view.song_key.text as String
                val rootView = view.song_id_root_view.text as String
                Log.d("rootView", rootView)
                activity.runOnUiThread {
                    val bundle = Bundle()
                    bundle.putString("key", key)
                    when(rootView) {
                        R.id.rl_song_fragment.toString() -> {
                            fragment.playSongFragment.arguments = bundle
                            if (fragment.childFragmentManager.findFragmentByTag("play_song_fragment") != null) {
                                fragment.childFragmentManager.beginTransaction().replace(rootView.toInt(), fragment.playSongFragment, "play_song_fragment")
                                        .addToBackStack(null)
                                        .commit()
                            } else {
                                fragment.childFragmentManager.beginTransaction().add(rootView.toInt(), fragment.playSongFragment, "play_song_fragment")
                                        .addToBackStack(null)
                                        .commit()
                            }
                        }
                        R.id.rl_album_fragment.toString() ->{
                            albumFragment.playSongFragment.arguments = bundle
                            if (albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2") != null) {
                                albumFragment.childFragmentManager.beginTransaction().replace(rootView.toInt(), albumFragment.playSongFragment, "play_song_fragment2")
                                        .addToBackStack(null)
                                        .commit()
                            } else {
                                albumFragment.childFragmentManager.beginTransaction().add(rootView.toInt(), albumFragment.playSongFragment, "play_song_fragment2")
                                        .addToBackStack(null)
                                        .commit()
                            }
                        }
                        else ->{}
                    }
                }
            }
        }
    }

    fun onAlbumItemsListen(fragment: AlbumFragment){
        albumFragment = fragment
        fragment.album_list_view.setOnItemClickListener { parent, view, position, id ->
            activity.runOnUiThread {
                val key = view.album_key.text
                val bundle = Bundle()
                bundle.putString("key", key as String)
                fragment.songFragment.arguments = bundle
                if (fragment.childFragmentManager.findFragmentByTag("album_song_fragment") != null) {
                    fragment.childFragmentManager.beginTransaction().replace(R.id.rl_album_fragment, fragment.songFragment, "album_song_fragment")
                            .addToBackStack(null)
                            .commit()
                }else{
                    fragment.childFragmentManager.beginTransaction().add(R.id.rl_album_fragment, fragment.songFragment, "album_song_fragment")
                            .addToBackStack(null)
                            .commit()
                }
            }
        }
    }

    fun onMvItemsListen(fragment: MvFragment){
        mvFragment = fragment
        activity.mv_gird_view.setOnItemClickListener { parent, view, position, id ->
            activity.runOnUiThread {
                activity.runOnUiThread {
                    val key = view.mv_key.text
                    val videoId = view.mv_videoId.text
                    val bundle = Bundle()
                    bundle.putString("key", key as String)
                    bundle.putString("videoId", videoId as String)
                    mvFragment.playSongFragment.arguments = bundle
                    if (fragment.childFragmentManager.findFragmentByTag("play_song_fragment3") != null) {
                        fragment.childFragmentManager.beginTransaction().replace(R.id.rl_mv_fragment, mvFragment.playSongFragment, "play_song_fragment3")
                                .addToBackStack(null)
                                .commit()
                    }else{
                        fragment.childFragmentManager.beginTransaction().add(R.id.rl_mv_fragment, mvFragment.playSongFragment, "play_song_fragment3")
                                .addToBackStack(null)
                                .commit()
                    }
                }
            }
        }
    }

}