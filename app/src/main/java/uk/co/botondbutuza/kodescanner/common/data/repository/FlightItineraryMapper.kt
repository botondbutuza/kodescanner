package uk.co.botondbutuza.kodescanner.common.data.repository

import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightItinerary
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightLegModel
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightResponseModel

class FlightItineraryMapper(private val flightMapper: FlightMapper) {

    fun map(flightResponseModel: FlightResponseModel): List<FlightItinerary> {
        val routes = mutableListOf<FlightItinerary>()

        for (itinerary in flightResponseModel.itineraries) {
            val outbound = flightMapper.map(findLegFor(flightResponseModel.legs, itinerary.legs[0]))
            val inbound = flightMapper.map(findLegFor(flightResponseModel.legs, itinerary.legs[1]))

            routes.add(FlightItinerary(outbound, inbound, itinerary.price, itinerary.agent))
        }

        return routes
    }

    private fun findLegFor(legs: List<FlightLegModel>, legId: String) =
        legs.first { it.id == legId }
}