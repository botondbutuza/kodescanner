package uk.co.botondbutuza.kodescanner.ui.main

import io.reactivex.disposables.CompositeDisposable
import uk.co.botondbutuza.kodescanner.common.data.repository.FlightsRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val repository: FlightsRepository,
    private val view: MainContract.View
) : MainContract.Presenter {
    private val subscriptions = CompositeDisposable()

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun requestFlights() {
        subscriptions.add(
            repository
                .getFlights()
                .subscribe(
                    view::onFlightsReady,
                    view::onError
                )
        )
    }
}