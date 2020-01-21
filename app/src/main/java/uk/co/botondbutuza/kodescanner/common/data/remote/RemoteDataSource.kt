package uk.co.botondbutuza.kodescanner.common.data

import io.reactivex.Single
import uk.co.botondbutuza.kodescanner.common.data.models.FlightResponseModel


/**
 * Created by brotond on 26/01/2018.
 */

interface RemoteDataSource {

    fun posts(): Single<List<FlightResponseModel>>

    fun comments(): Single<List<Comment>>

    fun users(): Single<List<User>>
}
