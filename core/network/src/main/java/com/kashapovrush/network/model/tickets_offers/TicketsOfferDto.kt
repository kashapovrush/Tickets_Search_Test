package com.kashapovrush.network.model.tickets_offers

data class TicketsOfferDto(
    val id: Int,
    val price: PriceDto,
    val time_range: List<String>,
    val title: String
)