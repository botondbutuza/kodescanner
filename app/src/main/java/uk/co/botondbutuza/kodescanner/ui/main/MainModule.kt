package uk.co.botondbutuza.kodescanner.ui.main

import dagger.Module
import dagger.Provides
import uk.co.botondbutuza.kodescanner.ui.binders.CarrierLogoBinder
import uk.co.botondbutuza.kodescanner.ui.binders.FlightBinder
import uk.co.botondbutuza.kodescanner.ui.helpers.DateTimeFormatter

@Module
class MainModule(private val view: MainContract.View) {

    @Provides fun provideFlightBinder(): FlightBinder =
        FlightBinder(
            CarrierLogoBinder(),
            DateTimeFormatter()
        )

    @Provides fun provideView() = view
}