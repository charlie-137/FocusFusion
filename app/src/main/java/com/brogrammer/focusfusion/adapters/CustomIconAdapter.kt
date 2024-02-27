package com.brogrammer.focusfusion.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.brogrammer.focusfusion.R
import com.brogrammer.focusfusion.model.Icons

class CustomIconAdapter(
    context: Context,
    private val gridItems: Map<String, Int>, private val listener: OnIconClickListener) :
    ArrayAdapter<String>(context, R.layout.grid_item_icon, gridItems.keys.toList()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            itemView = LayoutInflater.from(context)
                .inflate(R.layout.grid_item_icon, parent, false)

            holder = ViewHolder()
            holder.imageView = itemView.findViewById(R.id.iconImageView)

            itemView.tag = holder
        } else {
            holder = itemView?.tag as ViewHolder
        }

        val currentItemName = gridItems.keys.elementAt(position)
        val currentItemDrawableId = gridItems[currentItemName] ?: 0

        holder.imageView.setImageResource(currentItemDrawableId)

        // Set click listener to handle item selection
        itemView?.setOnClickListener {
            listener.onIconClicked(currentItemDrawableId, currentItemName)
        }

        return itemView!!
    }

    private class ViewHolder {
        lateinit var imageView: ImageView
    }

    interface OnIconClickListener{
        fun onIconClicked(iconDrawableId: Int, iconName: String)
    }
}
