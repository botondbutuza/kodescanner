package uk.co.botondbutuza.blogger.ui.post

import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User
import uk.co.botondbutuza.blogger.common.ui.BaseContract

interface PostDetailContract {

    interface View : BaseContract.View {

        fun onDetailsReady(post: Post, user: User, comments: List<Comment>)

    }

    interface Presenter : BaseContract.Presenter {

        fun requestPost(postId: Int)

    }
}