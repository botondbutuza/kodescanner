package uk.co.botondbutuza.kodescanner.ui.main

import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightItinerary
import uk.co.botondbutuza.kodescanner.common.ui.BaseContract

interface MainContract {

    interface View : BaseContract.View {

        fun onFlightsReady(flights: List<FlightItinerary>)
    }

    interface Presenter : BaseContract.Presenter {

        fun requestFlights()
    }
}