package com.kashapovrush.common.adapter.offers_tickets

import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.common.model.TicketsOffers

class DifUtilOffersTickets: DiffUtil.ItemCallback<TicketsOffers>() {
    override fun areItemsTheSame(oldItem: TicketsOffers, newItem: TicketsOffers): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TicketsOffers, newItem: TicketsOffers): Boolean {
        return oldItem == newItem
    }
}