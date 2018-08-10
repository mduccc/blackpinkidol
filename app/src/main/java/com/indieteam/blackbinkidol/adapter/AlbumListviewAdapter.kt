package com.indieteam.blackbinkidol.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.process.AlbumData
import com.indieteam.blackbinkidol.process.SongData
import kotlinx.android.synthetic.main.album_layout.view.*
import kotlinx.android.synthetic.main.song_layout.view.*

class AlbumListviewAdapter(val context: Context, val data: ArrayList<AlbumData>): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.album_layout, null)
        view.album_name.text = data[position].name
        view.album_genre.text = "Genre: "+data[position].genre
        view.album_label.text = "Lable: "+data[position].label
        view.album_year.text = "Year: "+data[position].year
        return view
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

}