package com.indieteam.blackbinkidol.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.model.AvatarData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.indieteam.blackbinkidol.ui.update.UpdateUi

class GirdviewAdapter(val activity: MainActivity, val data: ArrayList<AvatarData>): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_avatar, null)
//        view.avatar_key.text = data[position].key
//        view.avatar_image.layoutParams.height = 350
//        view.avatar_name.text = data[position].name
//
//        Picasso.get()
//                .load(data[position].imageProfile)
//                .resize(200,200)
//                .centerCrop()
//                .into(view.avatar_image)
        UpdateUi().layoutAvatar(view, data[position])
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