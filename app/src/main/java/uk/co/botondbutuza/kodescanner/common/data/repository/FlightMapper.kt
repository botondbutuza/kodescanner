package uk.co.botondbutuza.kodescanner.common.data.repository

import uk.co.botondbutuza.kodescanner.common.data.local.models.Flight
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightLegModel

class FlightMapper {

    fun map(leg: FlightLegModel): Flight {
        return Flight(
            leg.departure_time, leg.departure_airport, leg.arrival_time, leg.arrival_airport, leg.stops, leg.airline_name, leg.airline_id
        )
    }
}