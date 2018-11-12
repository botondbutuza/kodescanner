package uk.co.botondbutuza.blogger.ui.post

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User
import uk.co.botondbutuza.blogger.common.data.repository.BlogDataRepository
import javax.inject.Inject

class PostDetailPresenter @Inject constructor(
    private val repository: BlogDataRepository,
    private val view: PostDetailContract.View
) : PostDetailContract.Presenter {
    private val subscriptions = CompositeDisposable()

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun requestPost(postId: Int) {
        subscriptions.add(
            repository
                .getPost(postId)
                .flatMap { Single
                    /*
                     * there's loads of ways of doing this, I went with this now for simplicity's sake;
                     * I know it's a tad ugly, but it only requests data when its needed, and caches it all for the next
                     * go round (offline first eh).
                     *
                     * Depending on the use case I could pre-load all data and create the Post
                     * model with associated User and Comment objects linked in realm, in which case the details presenter
                     * would only need to load the Post and everything else is already accessible through that.
                     */
                    .zip(
                        Single.just(it),
                        repository.getUser(it.userId),
                        repository.getComments(it.id),
                        Function3<Post, User, List<Comment>, Triple<Post, User, List<Comment>>> { post, user, comments ->
                            return@Function3 Triple(post, user, comments)
                        }
                    )
                }
                .subscribe(
                    { view.onDetailsReady(it.first, it.second, it.third) },
                    view::onError
                )
        )
    }
}