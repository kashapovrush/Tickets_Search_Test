package com.kashapovrush.common.adapter.offers


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kashapovrush.common.R
import com.kashapovrush.common.model.Offer

class OffersAdapter: ListAdapter<Offer, OffersViewHolder>(DiffUtilOffers()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        return OffersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_offers,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val item = getItem(position)

        holder.artist.text = item.artist
        holder.city.text = item.city
        holder.price.text = "от ${item.price} ₽"

        when(item.id) {
            1 -> holder.image.setImageResource(com.kashapovrush.palette.R.drawable.id1)
            2 -> holder.image.setImageResource(com.kashapovrush.palette.R.drawable.id2)
            3 -> holder.image.setImageResource(com.kashapovrush.palette.R.drawable.id3)
        }
    }
}