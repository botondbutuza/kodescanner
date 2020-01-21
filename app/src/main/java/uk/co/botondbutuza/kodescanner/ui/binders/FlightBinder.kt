package uk.co.botondbutuza.kodescanner.ui.binders

import android.view.View
import kotlinx.android.synthetic.main.item_flight_itinerary.view.*
import uk.co.botondbutuza.kodescanner.common.data.local.models.Flight
import uk.co.botondbutuza.kodescanner.ui.helpers.DateTimeFormatter

class FlightBinder(
    private val carrierLogoBinder: CarrierLogoBinder,
    private val dateTimeFormatter: DateTimeFormatter
) {

    fun bind(itemView: View, outbound: Flight, inbound: Flight) {
        with (itemView) {
            carrierLogoBinder.bind(outbound_image, outbound.carrierId)
            carrierLogoBinder.bind(inbound_image, inbound.carrierId)

            // todo: create string resources and a string interactor to properly abstract string manipulation
            // todo: create string plural for stop/stops
            outbound_time.text = dateTimeFormatter.getTime(outbound.departureTime) + " - " + dateTimeFormatter.getTime(outbound.arrivalTime)
            outbound_duration.text = dateTimeFormatter.getDifference(outbound.departureTime, outbound.arrivalTime)
            outbound_connection.text = if (outbound.stops == 0) "Direct" else outbound.stops.toString() + " stops"
            outbound_location.text = outbound.departureAirport + "-" + outbound.arrivalAirport + ", " + outbound.carrier

            inbound_time.text = dateTimeFormatter.getTime(inbound.departureTime) + " - " + dateTimeFormatter.getTime(inbound.arrivalTime)
            inbound_duration.text = dateTimeFormatter.getDifference(inbound.departureTime, inbound.arrivalTime)
            inbound_connection.text = if (inbound.stops == 0) "Direct" else inbound.stops.toString() + " stops"
            inbound_location.text = inbound.departureAirport + "-" + inbound.arrivalAirport + ", " + inbound.carrier
        }
    }
}