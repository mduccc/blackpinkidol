package com.indieteam.blackbinkidol.ui.events

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.fragment.*
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
    private val playSongFragment = PlaySongFragment()
    private val playSongFragment2 = PlaySongFragment()
    private val songFragment = SongFragment()
    private lateinit var albumFragment: AlbumFragment

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
                    if(rootView == R.id.rl_song_fragment.toString()) {
                        playSongFragment.arguments = bundle
                        if (fragment.childFragmentManager.findFragmentByTag("play_song_fragment") != null) {
                            fragment.childFragmentManager.beginTransaction().replace(rootView.toInt(), playSongFragment, "play_song_fragment")
                                    .addToBackStack(null)
                                    .commit()
                        } else {
                            fragment.childFragmentManager.beginTransaction().add(rootView.toInt(), playSongFragment, "play_song_fragment")
                                    .addToBackStack(null)
                                    .commit()
                        }
                    }else{
                        playSongFragment2.arguments = bundle
                        if (albumFragment.childFragmentManager.findFragmentByTag("play_song_fragment2") != null) {
                            albumFragment.childFragmentManager.beginTransaction().replace(rootView.toInt(), playSongFragment2, "play_song_fragment2")
                                    .addToBackStack(null)
                                    .commit()
                        } else {
                            albumFragment.childFragmentManager.beginTransaction().add(rootView.toInt(), playSongFragment2, "play_song_fragment2")
                                    .addToBackStack(null)
                                    .commit()
                        }
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
                songFragment.arguments = bundle
                if (fragment.childFragmentManager.findFragmentByTag("song_fragment") != null) {
                    fragment.childFragmentManager.beginTransaction().replace(R.id.rl_album_fragment, songFragment, "song_fragment")
                            .addToBackStack(null)
                            .commit()
                }else{
                    fragment.childFragmentManager.beginTransaction().add(R.id.rl_album_fragment, songFragment, "song_fragment")
                            .addToBackStack(null)
                            .commit()
                }
            }
        }
    }

    fun onMvItemsListen(fragment: MvFragment){
        activity.mv_gird_view.setOnItemClickListener { parent, view, position, id ->
            activity.runOnUiThread {
                Toast.makeText(activity, view.mv_key.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

}