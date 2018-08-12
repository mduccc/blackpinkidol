package com.indieteam.blackbinkidol.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.api.Api
import com.indieteam.blackbinkidol.model.MvData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.update.UpdateUi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.mv_layout.view.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MvGirdviewAdapter(val activity: MainActivity, val data: ArrayList<MvData>): BaseAdapter(){
    val client = OkHttpClient()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.mv_layout, null)
        UpdateUi().layoutMv(activity, client, view, data[position])
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