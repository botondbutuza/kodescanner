package uk.co.botondbutuza.kodescanner.common.data.local.models

data class Flight(
    val departureTime: String,
    val departureAirport: String,
    val arrivalTime: String,
    val arrivalAirport: String,
    val stops: Int,
    val carrier: String,
    val carrierId: String
)