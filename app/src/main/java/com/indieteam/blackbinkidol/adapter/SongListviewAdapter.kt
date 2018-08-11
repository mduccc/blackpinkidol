package com.indieteam.blackbinkidol.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.model.SongData
import kotlinx.android.synthetic.main.song_layout.view.*

class SongListviewAdapter(val context: Context, val data: ArrayList<SongData>): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.song_layout, null)
        view.song_key.text = data[position].key
        view.song_name.text = data[position].name
        view.song_group.text = "Singer: "+ data[position].info
        view.song_year.text = "Year: "+ data[position].year
        view.song_id_root_view.text = data[position].rootView
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