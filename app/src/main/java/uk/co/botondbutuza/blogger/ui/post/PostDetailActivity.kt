package uk.co.botondbutuza.blogger.ui.post

import android.content.Context
import android.content.Intent
import kotlinx.android.synthetic.main.item_post_details.*
import uk.co.botondbutuza.blogger.R
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User
import uk.co.botondbutuza.blogger.common.ui.BaseActivity
import javax.inject.Inject

class PostDetailActivity : BaseActivity(R.layout.activity_post_detail), PostDetailContract.View {

    companion object {

        private const val EXTRA_POST_ID = "EXTRA_POST_ID"

        internal fun launch(context: Context, post: Post) {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, post.id)
            context.startActivity(intent)
        }
    }


    @Inject internal lateinit var presenter: PostDetailPresenter
    private val postId by lazy { intent.getIntExtra(EXTRA_POST_ID, -1) }


    override fun initViews() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.requestPost(postId)
    }

    override fun teardown() {
        presenter.unsubscribe()
    }

    override fun injectDagger() {
        DaggerPostDetailComponent.builder()
            .repositoryComponent(app().repositoryComponent)
            .postDetailModule(PostDetailModule(this))
            .build().inject(this)
    }


    // PostDetailContract.View implementation.

    override fun onDetailsReady(post: Post, user: User, comments: List<Comment>) {
        postTitle.text = post.title
        body.text = post.body

        username.text = getString(R.string.by_username, user.username)
        commentCount.text = getString(R.string.comment_count, comments.size)
    }
}