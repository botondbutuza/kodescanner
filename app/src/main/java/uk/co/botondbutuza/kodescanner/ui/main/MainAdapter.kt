package uk.co.botondbutuza.kodescanner.ui.main

import android.view.View
import kotlinx.android.synthetic.main.item_flight_itinerary.view.*
import uk.co.botondbutuza.kodescanner.R
import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightItinerary
import uk.co.botondbutuza.kodescanner.common.ui.BaseRecyclerViewAdapter
import uk.co.botondbutuza.kodescanner.common.ui.BaseRecyclerViewHolder
import uk.co.botondbutuza.kodescanner.ui.binders.FlightBinder
import javax.inject.Inject

class MainAdapter @Inject constructor(
    private val flightBinder: FlightBinder
) : BaseRecyclerViewAdapter<FlightItinerary, MainAdapter.Holder>(R.layout.item_flight_itinerary) {

    override fun createViewHolderForView(view: View, viewType: Int) = Holder(view)


    inner class Holder(itemView: View) : BaseRecyclerViewHolder<FlightItinerary>(itemView) {

        override fun bind(item: FlightItinerary) {
            flightBinder.bind(itemView, item.outbound, item.inbound)

            itemView.price.text = item.price
            itemView.agent.text = "via " + item.agent
        }

        override fun unbind() {

        }
    }
}