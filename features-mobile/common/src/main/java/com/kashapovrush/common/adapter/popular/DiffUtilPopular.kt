package com.kashapovrush.common.adapter.popular

import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.common.model.Popular

class DiffUtilPopular: DiffUtil.ItemCallback<Popular>() {
    override fun areItemsTheSame(oldItem: Popular, newItem: Popular): Boolean {
        return oldItem.town == newItem.town
    }

    override fun areContentsTheSame(oldItem: Popular, newItem: Popular): Boolean {
        return oldItem == newItem
    }
}