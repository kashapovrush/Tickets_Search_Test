package com.kashapovrush.common.adapter.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kashapovrush.common.R
import com.kashapovrush.common.model.Popular

class PopularAdapter : ListAdapter<Popular, PopularViewHolder>(DiffUtilPopular()) {

    var onItemClickListener: ((Popular) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = getItem(position)

        holder.town.text = item.town

        holder.view.setOnClickListener {
            onItemClickListener?.invoke(item)
        }

        when(item.town) {
            "Стамбул" -> holder.image.setImageResource(com.kashapovrush.palette.R.drawable.stambul)
            "Сочи" -> holder.image.setImageResource(com.kashapovrush.palette.R.drawable.sochi)
            "Пхукет" -> holder.image.setImageResource(com.kashapovrush.palette.R.drawable.phuket)
        }
    }
}