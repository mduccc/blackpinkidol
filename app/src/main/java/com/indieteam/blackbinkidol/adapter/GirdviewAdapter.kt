package com.indieteam.blackbinkidol.adapter

import android.content.Context
import android.text.Layout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import com.indieteam.blackbinkidol.activity.MainActivity

class GirdviewAdapter(val context: Context, val layout: List<Int>): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(context)
        textView.let {
            it.text = layout[position].toString()
            it.gravity = Gravity.CENTER
        }
        return textView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return layout.size
    }

}