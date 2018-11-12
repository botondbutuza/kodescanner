package uk.co.botondbutuza.blogger.ui.main

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.botondbutuza.blogger.R
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.ui.BaseActivity
import uk.co.botondbutuza.blogger.common.ui.OnRecyclerItemTouchListener
import uk.co.botondbutuza.blogger.ui.post.PostDetailActivity
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main), MainContract.View, OnRecyclerItemTouchListener.OnItemClickListener {

    @Inject internal lateinit var presenter: MainPresenter
    @Inject internal lateinit var adapter: MainAdapter
    private val itemTouchListener by lazy { OnRecyclerItemTouchListener(this, this) }


    override fun initViews() {
        scroll.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        scroll.adapter = adapter
        scroll.addOnItemTouchListener(itemTouchListener)

        refresh.setOnRefreshListener { presenter.requestPostsRemote() }
        presenter.requestPosts()
    }

    override fun teardown() {
        presenter.unsubscribe()
        scroll.removeOnItemTouchListener(itemTouchListener)
    }

    override fun injectDagger() {
        DaggerMainComponent.builder()
            .repositoryComponent(app().repositoryComponent)
            .mainModule(MainModule(this))
            .build().inject(this)
    }


    // OnRecyclerItemTouchListener.OnItemClickListener implementation.

    override fun onItemClick(view: View, viewHolder: RecyclerView.ViewHolder, position: Int): Boolean {
        PostDetailActivity.launch(this, adapter.getItemAt(position))
        return true
    }


    // MainContract.View implementation.

    override fun onPostsReady(posts: List<Post>) {
        refresh.isRefreshing = false
        adapter.setItems(posts)
    }
}
