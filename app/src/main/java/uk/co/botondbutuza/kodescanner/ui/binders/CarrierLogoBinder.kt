package uk.co.botondbutuza.kodescanner.ui.main

import android.widget.ImageView
import com.squareup.picasso.Picasso

class CarrierLogoBinder {

    fun bind(imageView: ImageView, carrierId: String) {

        Picasso.get()
            .load("https://logos.skyscnr.com/images/airlines/small/$carrierId.png")
            .into(imageView)
    }
}