package uk.co.botondbutuza.kodescanner.common.data.repository

import io.reactivex.Single
import uk.co.botondbutuza.kodescanner.common.dagger.scope.Remote
import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightRoute
import uk.co.botondbutuza.kodescanner.common.data.remote.RemoteDataSource


class FlightsInteractor(
    @param:Remote @field:Remote private val remoteDataSource: RemoteDataSource,
    private val flightsMapper: FlightsMapper
) {

    fun getFlights(): Single<List<FlightRoute>> =
        remoteDataSource
            .getFlights()
            .map { flightsMapper.map(it) }
}
