package uk.co.botondbutuza.blogger.common.ui

interface BaseContract {

    interface View {

        fun onError(e: Throwable)
    }

    interface Presenter {

        fun unsubscribe()
    }
}