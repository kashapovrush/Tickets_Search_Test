package com.kashapovrush.network.model.tickets

data class TicketDto(
    val arrival: ArrivalDto,
    val badge: String,
    val company: String,
    val departure: DepartureDto,
    val has_transfer: Boolean,
    val id: Int,
    val price: PriceXDto
)