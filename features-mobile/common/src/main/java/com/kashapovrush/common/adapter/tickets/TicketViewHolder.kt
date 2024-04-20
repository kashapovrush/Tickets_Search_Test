package com.kashapovrush.common.adapter.tickets

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kashapovrush.common.R

class TicketViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val price = view.findViewById<TextView>(R.id.price_ticket)
    val departureDate = view.findViewById<TextView>(R.id.departure_date)
    val departureAirport = view.findViewById<TextView>(R.id.departure_airport)
    val arrivalDate = view.findViewById<TextView>(R.id.arrival_date)
    val arrivalAirport = view.findViewById<TextView>(R.id.arrival_airport)
    val flightTime = view.findViewById<TextView>(R.id.flight_time)
    val badge = view.findViewById<TextView>(R.id.badge_text)
    val badgeLayout = view.findViewById<FrameLayout>(R.id.badge_layout)
    val hasTransfer = view.findViewById<TextView>(R.id.has_transfer)
}