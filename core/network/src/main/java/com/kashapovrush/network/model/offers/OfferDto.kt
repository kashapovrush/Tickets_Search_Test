package com.kashapovrush.network.model.offers

data class OfferDto(
//    @SerializedName("id")
    val id: Int,
//    @SerializedName("title")
    val title: String,
//    @SerializedName("town")
    val town: String,
//    @SerializedName("price")
    val price: PriceDto
)
