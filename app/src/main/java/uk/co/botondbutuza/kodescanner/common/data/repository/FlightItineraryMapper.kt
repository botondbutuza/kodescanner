package uk.co.botondbutuza.kodescanner.common.data.repository

import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightItinerary
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightResponseModel

class FlightsMapper {

    fun map(flightResponseModel: FlightResponseModel): List<FlightItinerary> {
        val routes = mutableListOf<FlightItinerary>()

        for (itinerary in flightResponseModel.itineraries) {

            routes.add(FlightItinerary(outbound, inbound, itinerary.price, itinerary.agent))
        }

        return routes
    }
}