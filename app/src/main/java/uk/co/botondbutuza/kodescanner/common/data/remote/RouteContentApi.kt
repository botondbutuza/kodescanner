package uk.co.botondbutuza.kodescanner.common.data

import io.reactivex.Single
import retrofit2.http.GET
import uk.co.botondbutuza.kodescanner.common.data.models.FlightResponseModel

interface RouteContentApi {

    @GET("flights.json")
    fun getFlights(): Single<List<FlightResponseModel>>

}
