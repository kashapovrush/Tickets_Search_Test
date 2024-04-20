package com.kashapovrush.common.adapter.tickets

import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.common.model.Ticket

class DiffUtilTicket : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }
}