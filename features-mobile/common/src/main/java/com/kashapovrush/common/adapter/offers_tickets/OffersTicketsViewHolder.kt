package com.kashapovrush.common.adapter.offers_tickets

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.common.R

class OffersTicketsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val titleCompany = view.findViewById<TextView>(R.id.title_company)
    val rangeTime = view.findViewById<TextView>(R.id.range_time)
    val price = view.findViewById<TextView>(R.id.price)
    val imageCompany = view.findViewById<ImageView>(R.id.logo)
}