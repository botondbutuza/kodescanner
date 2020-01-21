package uk.co.botondbutuza.kodescanner.common.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightResponseModel

interface RouteContentApi {

    @GET("flights.json")
    fun getFlights(): Single<FlightResponseModel>

}
