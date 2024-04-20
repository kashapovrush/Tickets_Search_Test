package com.kashapovrush.common.adapter.offers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.common.R

class OffersViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val image = view.findViewById<ImageView>(R.id.image_flight_with_music)
    val city = view.findViewById<TextView>(R.id.city)
    val artist = view.findViewById<TextView>(R.id.artist)
    val price = view.findViewById<TextView>(R.id.price)
}