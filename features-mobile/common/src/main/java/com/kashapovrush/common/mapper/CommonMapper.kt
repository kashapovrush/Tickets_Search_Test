package com.kashapovrush.common.mapper

import android.text.TextUtils
import com.kashapovrush.common.model.Offer
import com.kashapovrush.common.model.Ticket
import com.kashapovrush.common.model.TicketsOffers
import com.kashapovrush.network.model.offers.OfferDto
import com.kashapovrush.network.model.tickets.TicketDto
import com.kashapovrush.network.model.tickets_offers.TicketsOfferDto
import javax.inject.Inject

class CommonMapper @Inject constructor() {

    fun mapOffersDtoToOffers(offerDto: OfferDto): Offer {
        return Offer(
            id = offerDto.id,
            artist = offerDto.title,
            city = offerDto.town,
            price = offerDto.price.value.toString()
        )
    }

    fun mapOffersListDtoToOffersList(list: List<OfferDto>): List<Offer> {
        return list.map {
            mapOffersDtoToOffers(it)
        }
    }

    fun mapOffersTicketsDtoToOffersTickets(ticketsOffersDto: TicketsOfferDto): TicketsOffers {
        return TicketsOffers(
            id = ticketsOffersDto.id,
            title = ticketsOffersDto.title,
            price = ticketsOffersDto.price.value,
            rangeTime = TextUtils.join(" ", ticketsOffersDto.time_range)
        )
    }

    fun mapOffersTicketsListDtoToOffersTicketsList(list: List<TicketsOfferDto>): List<TicketsOffers> {
        return list.map {
            mapOffersTicketsDtoToOffersTickets(it)
        }
    }

    fun mapTicketDtoToTicket(ticket: TicketDto): Ticket {
        return Ticket(
            id = ticket.id,
            badge = ticket.badge,
            arrivalAirport = ticket.arrival.airport,
            arrivalDate = ticket.arrival.date,
            departureDate = ticket.departure.date,
            departureAirport = ticket.departure.airport,
            price = ticket.price.value,
            hasTransfer = ticket.has_transfer
        )
    }

    fun mapTicketsDtoListToTicketsList(list: List<TicketDto>): List<Ticket> {
        return list.map {
            mapTicketDtoToTicket(it)
        }
    }
}