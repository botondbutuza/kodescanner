package uk.co.botondbutuza.kodescanner.common.data.models


data class FlightResponseModel(
    val itineraryModels: List<FlightItineraryModel>,
    val legs: List<FlightLegModel>
)