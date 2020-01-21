package uk.co.botondbutuza.kodescanner.common.ui

interface BaseContract {

    interface View {

        fun onError(e: Throwable)
    }

    interface Presenter {

        fun unsubscribe()
    }
}