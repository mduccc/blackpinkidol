package com.indieteam.blackbinkidol.adapter

import android.content.Context
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.R.id.gird_view
import com.indieteam.blackbinkidol.process.AvatarData
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.avatar_layout.view.*
import kotlinx.android.synthetic.main.fragment_profile.*

class GirdviewAdapter(val context: Context, val data: ArrayList<AvatarData>): BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutAvatar = layoutInflater.inflate(R.layout.avatar_layout, null)
        layoutAvatar.avatar_image.setImageResource(R.drawable.singer_load)
        layoutAvatar.avatar_image.layoutParams.height = 350
        layoutAvatar.avatar_name.text = data[position].name

        Picasso.get()
                .load(data[position].imageProfile)
                .resize(200,200)
                .centerCrop()
                .into(layoutAvatar.avatar_image)
        return layoutAvatar
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