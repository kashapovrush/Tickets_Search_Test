package com.kashapovrush.common.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kashapovrush.common.mapper.CommonMapper
import com.kashapovrush.common.model.Offer
import com.kashapovrush.common.model.Ticket
import com.kashapovrush.common.model.TicketsOffers
import com.kashapovrush.network.api.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommonViewModel @Inject constructor(
    private val apiService: ApiService,
    private val mapper: CommonMapper
) : ViewModel() {

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> = _offers

    private val _offersTickets = MutableLiveData<List<TicketsOffers>>()
    val offersTickets: LiveData<List<TicketsOffers>> = _offersTickets

    private val _tickets = MutableLiveData<List<Ticket>>()
    val tickets: LiveData<List<Ticket>> = _tickets

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    suspend fun getOffers() = flow {
        emit(apiService.getOffers())
    }.map {
        mapper.mapOffersListDtoToOffersList(it.offers)
    }.catch {
        _error.postValue(it.message)
    }.collect {
        _offers.postValue(it)
    }

    suspend fun getOffersTickets() = flow {
        emit(apiService.getOffersTickets())
    }.map {
        mapper.mapOffersTicketsListDtoToOffersTicketsList(it.tickets_offers)
    }.catch {
        _error.postValue(it.message)
    }.collect {
        _offersTickets.postValue(it)
    }

    suspend fun getTickets() = flow {
        emit(apiService.getTickets())
    }.map {
        mapper.mapTicketsDtoListToTicketsList(it.tickets)
    }.catch {
        _error.postValue(it.message)
    }.collect {
        _tickets.postValue(it)
    }


}