package uk.co.botondbutuza.blogger.ui.main

import io.reactivex.disposables.CompositeDisposable
import uk.co.botondbutuza.blogger.common.data.repository.BlogDataRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val repository: BlogDataRepository,
    private val view: MainContract.View
) : MainContract.Presenter {
    private val subscriptions = CompositeDisposable()

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun requestPosts() {
        subscriptions.add(
            repository
                .getPosts()
                .subscribe(
                    view::onPostsReady,
                    view::onError
                )
        )
    }

    override fun requestPostsRemote() {
        subscriptions.add(
            repository
                .getPostsRemote()
                .subscribe(
                    view::onPostsReady,
                    view::onError
                )
        )
    }
}