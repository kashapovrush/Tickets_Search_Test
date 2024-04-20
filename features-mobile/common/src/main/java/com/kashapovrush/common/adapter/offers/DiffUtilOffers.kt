package com.kashapovrush.common.adapter.offers

import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.common.model.Offer

class DiffUtilOffers: DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(
        oldItem: Offer,
        newItem: Offer
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Offer,
        newItem: Offer
    ): Boolean {
        return newItem == oldItem
    }

}