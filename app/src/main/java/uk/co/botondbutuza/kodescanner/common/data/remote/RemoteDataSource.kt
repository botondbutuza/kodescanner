package uk.co.botondbutuza.kodescanner.common.data.remote

import io.reactivex.Single
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightResponseModel


/**
 * Created by brotond on 26/01/2018.
 */

interface RemoteDataSource {

    fun getFlights(): Single<FlightResponseModel>

}
