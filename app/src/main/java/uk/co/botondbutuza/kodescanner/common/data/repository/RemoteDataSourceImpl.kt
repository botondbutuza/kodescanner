package uk.co.botondbutuza.kodescanner.common.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.botondbutuza.kodescanner.common.data.remote.RemoteDataSource
import uk.co.botondbutuza.kodescanner.common.data.remote.RouteContentApi
import uk.co.botondbutuza.kodescanner.common.data.remote.models.FlightResponseModel

class RemoteDataSourceImpl(private val api: RouteContentApi) : RemoteDataSource {

    override fun getFlights(): Single<FlightResponseModel> = api
        .getFlights()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}