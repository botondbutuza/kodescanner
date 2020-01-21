package uk.co.botondbutuza.blogger.ui.main

import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.ui.BaseContract

interface MainContract {

    interface View : BaseContract.View {

        fun onPostsReady(posts: List<Post>)
    }

    interface Presenter : BaseContract.Presenter {

        fun requestPosts()

        fun requestPostsRemote()
    }
}