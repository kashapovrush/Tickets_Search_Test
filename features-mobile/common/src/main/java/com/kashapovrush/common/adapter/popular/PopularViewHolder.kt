package com.kashapovrush.common.adapter.popular

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.common.R

class PopularViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val town = view.findViewById<TextView>(R.id.popular_town)
    val image = view.findViewById<ImageView>(R.id.image_popular)
}