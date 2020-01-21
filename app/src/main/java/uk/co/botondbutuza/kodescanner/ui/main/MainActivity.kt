package uk.co.botondbutuza.kodescanner.ui.main

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.botondbutuza.kodescanner.R
import uk.co.botondbutuza.kodescanner.common.data.local.models.FlightItinerary
import uk.co.botondbutuza.kodescanner.common.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main), MainContract.View {

    @Inject internal lateinit var presenter: MainPresenter
    @Inject internal lateinit var adapter: MainAdapter

    override fun initViews() {
        scroll.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        scroll.adapter = adapter

        refresh.setOnRefreshListener { presenter.requestFlights() }
        presenter.requestFlights()
    }

    override fun teardown() {
        presenter.unsubscribe()
    }

    override fun injectDagger() {
        DaggerMainComponent.builder()
            .repositoryComponent(app().repositoryComponent)
            .mainModule(MainModule(this))
            .build().inject(this)
    }


    // MainContract.View implementation.

    override fun onFlightsReady(flights: List<FlightItinerary>) {
        refresh.isRefreshing = false
        adapter.setItems(flights)
    }
}
