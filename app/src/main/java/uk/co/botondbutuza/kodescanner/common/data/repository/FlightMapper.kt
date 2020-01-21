package uk.co.botondbutuza.kodescanner.common.data.repository

import uk.co.botondbutuza.kodescanner.common.data.local.models.Flight
import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightRoute
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightLegModel

class FlightRouteMapper {

    fun map(leg: FlightLegModel): FlightRoute {
        return FlightRoute(
            Flight("time", "airport", leg.arrival_time, leg.departure_airport),
            Flight("time", "airport", "time", leg.arrival_airport),
            leg.airline_name
        )
    }
}