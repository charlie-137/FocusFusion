package com.brogrammer.focusfusion.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.brogrammer.focusfusion.R

class IconAdapter(context: Context, private val iconList: List<Int>) : ArrayAdapter<Int>(context, R.layout.grid_item_icon, iconList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView = convertView as? ImageView ?: LayoutInflater.from(context).inflate(R.layout.grid_item_icon, parent, false) as ImageView
        imageView.setImageResource(iconList[position])
        return imageView
    }
}


