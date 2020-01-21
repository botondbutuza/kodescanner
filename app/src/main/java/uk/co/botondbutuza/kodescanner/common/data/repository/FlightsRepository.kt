package uk.co.botondbutuza.kodescanner.common.data.repository

import io.reactivex.Single
import uk.co.botondbutuza.kodescanner.common.dagger.scope.Remote
import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightItinerary
import uk.co.botondbutuza.kodescanner.common.data.remote.RemoteDataSource


class FlightsRepository(
    @param:Remote @field:Remote private val remoteDataSource: RemoteDataSource,
    private val flightItineraryMapper: FlightItineraryMapper
) {

    fun getFlights(): Single<List<FlightItinerary>> =
        remoteDataSource
            .getFlights()
            .map { flightItineraryMapper.map(it) }
}
