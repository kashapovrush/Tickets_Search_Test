package com.kashapovrush.common.adapter.offers_tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kashapovrush.common.R
import com.kashapovrush.common.model.TicketsOffers

class OffersTicketsAdapter: ListAdapter<TicketsOffers, OffersTicketsViewHolder>(DifUtilOffersTickets()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersTicketsViewHolder {
        return OffersTicketsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_offers_tickets, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OffersTicketsViewHolder, position: Int) {
        val item = getItem(position)

        holder.titleCompany.text = item.title
        holder.price.text = "${item.price.addSeparator()} ₽"
        holder.rangeTime.text = item.rangeTime

        when(item.title) {
            "Уральские авиалинии" -> {
                holder.imageCompany.setImageResource(com.kashapovrush.palette.R.drawable.logo_red)
            }
            "Победа" -> {
                holder.imageCompany.setImageResource(com.kashapovrush.palette.R.drawable.logo_blue)
            }
            "NordStar" -> {
                holder.imageCompany.setImageResource(com.kashapovrush.palette.R.drawable.logo_white)
            }
        }
    }

    fun Int.addSeparator(): String {
        return String.format("%,d", this).replace(',', ' ')
    }
}