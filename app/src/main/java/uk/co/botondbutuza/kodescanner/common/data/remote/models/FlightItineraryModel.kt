package uk.co.botondbutuza.kodescanner.common.data.remote.models

data class FlightItineraryModel(
    val id: String,
    val legs: List<String>,
    val price: String,
    val agent: String,
    val agent_rating: Float
)