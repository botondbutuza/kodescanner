package uk.co.botondbutuza.blogger.ui.main

import android.view.View
import kotlinx.android.synthetic.main.item_post.view.*
import uk.co.botondbutuza.blogger.R
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.ui.BaseRecyclerViewAdapter
import uk.co.botondbutuza.blogger.common.ui.BaseRecyclerViewHolder
import javax.inject.Inject

class MainAdapter @Inject constructor() : BaseRecyclerViewAdapter<Post, MainAdapter.Holder>(R.layout.item_post) {

    override fun createViewHolderForView(view: View, viewType: Int) = Holder(view)


    class Holder(itemView: View) : BaseRecyclerViewHolder<Post>(itemView) {

        override fun bind(item: Post) {
            itemView.title.text = item.title
        }

        override fun unbind() {

        }
    }
}