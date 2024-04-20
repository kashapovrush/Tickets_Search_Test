package com.kashapovrush.common.model

data class Ticket(
    val id: Int,
    val badge: String? = null,
    val price: Int,
    val departureDate: String,
    val departureAirport: String,
    val arrivalDate: String,
    val arrivalAirport: String,
    val hasTransfer: Boolean
)
