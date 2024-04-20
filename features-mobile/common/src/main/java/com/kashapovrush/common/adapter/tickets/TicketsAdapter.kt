package com.kashapovrush.common.adapter.tickets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kashapovrush.common.R
import com.kashapovrush.common.model.Ticket
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TicketsAdapter: ListAdapter<Ticket, TicketViewHolder>(DiffUtilTicket()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tickets, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val item = getItem(position)

        holder.price.text = " ${item.price.addSeparator()} ₽"

        holder.departureDate.text = convertTime(item.departureDate)
        holder.departureAirport.text = item.departureAirport
        holder.arrivalDate.text = convertTime(item.arrivalDate)
        holder.arrivalAirport.text = item.arrivalAirport
        holder.flightTime.text = "${calculateFlightTime(item.departureDate, item.arrivalDate)} в пути"

        if (item.hasTransfer) {
            holder.hasTransfer.visibility = View.INVISIBLE
        } else {
            holder.hasTransfer.visibility = View.VISIBLE
        }

        if (item.badge != null) {
            holder.badge.text = item.badge
            holder.badgeLayout.visibility = View.VISIBLE
            holder.badgeLayout.bringToFront()
        }
    }

    fun convertTime(inputTime: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = inputFormat.parse(inputTime)
        return outputFormat.format(date)
    }

    fun calculateFlightTime(departure: String, arrival: String): String {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss"
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val timeStart = LocalDateTime.parse(departure, formatter)
        val timeEnd = LocalDateTime.parse(arrival, formatter)

        val duration = Duration.between(timeStart, timeEnd)

        val time = duration.toMinutes()

        val hours = time / 60
        val minute = time % 60
        return "$hours ч. $minute мин."
    }

    fun Int.addSeparator(): String {
        return String.format("%,d", this).replace(',', ' ')
    }
}