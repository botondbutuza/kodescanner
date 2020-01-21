package uk.co.botondbutuza.kodescanner.common.data.local.models

data class FlightItinerary(
    val outbound: Flight,
    val inbound: Flight,
    val price: String,
    val agent: String
)