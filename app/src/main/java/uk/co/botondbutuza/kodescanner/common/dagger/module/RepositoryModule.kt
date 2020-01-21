package uk.co.botondbutuza.kodescanner.common.dagger.module

import dagger.Module
import dagger.Provides
import io.reactivex.plugins.RxJavaPlugins
import uk.co.botondbutuza.kodescanner.common.dagger.scope.Remote
import uk.co.botondbutuza.kodescanner.common.data.remote.RemoteDataSource
import uk.co.botondbutuza.kodescanner.common.data.repository.FlightItineraryMapper
import uk.co.botondbutuza.kodescanner.common.data.repository.FlightMapper
import uk.co.botondbutuza.kodescanner.common.data.repository.FlightsRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides @Singleton
    internal fun provideFlightMapper(): FlightMapper = FlightMapper()

    @Provides @Singleton
    internal fun provideFlightItineraryMapper(flightMapper: FlightMapper): FlightItineraryMapper = FlightItineraryMapper(flightMapper)

    @Provides @Singleton
    internal fun provideRepository(@Remote remote: RemoteDataSource, itineraryMapper: FlightItineraryMapper): FlightsRepository {

        /*
         * Okay, so, if a stream is disposed before an exception can be thrown, Rx will *helpfully*
         * catch it and re-throw it wrapped in an UndeliverableException. So this call, which has to
         * go before any Rx call is made, swallows any such UndeliverableException.
         */
        RxJavaPlugins.setErrorHandler { }

        return FlightsRepository(remote, itineraryMapper)
    }
}
