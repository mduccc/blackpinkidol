package com.indieteam.blackbinkidol.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.model.MvData
import kotlinx.android.synthetic.main.mv_layout.view.*

class MvListviewAdapter(val context: Context, val data: ArrayList<MvData>): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.mv_layout, null)
        view.mv_name.text = data[position].name
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