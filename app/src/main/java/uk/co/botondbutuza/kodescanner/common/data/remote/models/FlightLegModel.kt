package uk.co.botondbutuza.kodescanner.common.data.models

data class FlightLegModel(
    val id: String,
    val departure_airport: String,
    val arrival_airport: String,
    val departure_time: String,
    val arrival_time: String,
    val stops: Int,
    val airline_name: String,
    val airline_id: String,
    val duration_mins: Int
)