package uk.co.botondbutuza.kodescanner.common.data.remote.models


data class FlightResponseModel(
    val itineraries: List<FlightItineraryModel>,
    val legs: List<FlightLegModel>
)