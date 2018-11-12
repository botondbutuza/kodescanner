package uk.co.botondbutuza.blogger.common.ui

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by brotond on 24/01/2018.
 */
abstract class BaseRecyclerViewAdapter<E, VH : BaseRecyclerViewHolder<E>>(
    @param:LayoutRes private val itemResId: Int
) : RecyclerView.Adapter<VH>() {
    private val items: MutableList<E> = ArrayList()


    // To implement.

    /**
     * Creates and returns a view holder, potentially based on the item type.
     */
    protected abstract fun createViewHolderForView(view: View, viewType: Int): VH

    /**
     * Can be overridden for further item type customisation.
     */
    @LayoutRes protected open fun getItemResId(viewType: Int) = itemResId


    // Data set manipulation.

    fun getItems() = items

    fun getItemAt(pos: Int): E = items[pos]

    fun setItems(items: List<E>) {
        val size = this.items.size

        if (size > 0) {
            this.items.clear()
            notifyItemRangeRemoved(0, size)
        }

        addItems(items)
    }

    fun addItems(items: List<E>) {
        val pos = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(pos, this.items.size)
    }

    fun addItem(item: E) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItem(item: E, pos: Int) {
        this.items.add(pos, item)
        notifyItemInserted(pos)
    }

    fun removeItem(pos: Int) {
        this.items.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun removeItems() {
        val size = this.items.size
        this.items.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun updateItem(item: E, pos: Int) {
        this.items[pos] = item
        notifyItemChanged(pos)
    }

    fun updateItems(items: List<E>) {
        val size = this.items.size
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeChanged(0, this.items.size)

        if (this.items.size > size) {           // we've added new items
            notifyItemRangeInserted(size, this.items.size)
        } else if (this.items.size < size) {    // we've removed items
            notifyItemRangeRemoved(this.items.size, size)
        }
    }


    // RecyclerView.Adapter implementations.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(getItemResId(viewType), parent, false)
        return createViewHolderForView(view, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItemAt(position))
    }

    override fun onViewRecycled(holder: VH) {
        holder.unbind()
        super.onViewRecycled(holder)
    }

    override fun getItemCount() = items.size

}
