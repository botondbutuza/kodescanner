package uk.co.botondbutuza.blogger.common.ui

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by brotond on 24/01/2018.
 */

abstract class BaseRecyclerViewHolder<in E>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: E)

    abstract fun unbind()
}
