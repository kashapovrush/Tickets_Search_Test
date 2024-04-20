package com.kashapovrush.network.api

import com.kashapovrush.network.model.offers.ResponseOffers
import com.kashapovrush.network.model.tickets.ResponseTickets
import com.kashapovrush.network.model.tickets_offers.ResponseTicketsOffers
import retrofit2.http.GET

interface ApiService {

    @GET("offers")
    suspend fun getOffers(): ResponseOffers

    @GET("offers_tickets")
    suspend fun getOffersTickets(): ResponseTicketsOffers

    @GET("tickets")
    suspend fun getTickets(): ResponseTickets

}