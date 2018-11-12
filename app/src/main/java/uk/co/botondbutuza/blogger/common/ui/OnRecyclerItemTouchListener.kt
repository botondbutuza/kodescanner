package uk.co.botondbutuza.blogger.common.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * Created by brotond on 19/01/2018.
 */

class OnRecyclerItemTouchListener(
    context: Context,
    private var listener: OnItemClickListener?
) : RecyclerView.OnItemTouchListener {
    private val gestureDetector: GestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }
    })

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)

        if (childView != null && listener != null && gestureDetector.onTouchEvent(e)) {
            val position = view.getChildAdapterPosition(childView)
            return listener!!.onItemClick(childView, view.findViewHolderForAdapterPosition(position), position)
        }

        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    interface OnItemClickListener {

        /**
         * Handles item clicks on the listAdapter.
         *
         * @param view       The view clicked.
         * @param viewHolder The view holder clicked.
         * @param position   The position of the item clicked.
         * @return True if the click is consumed, false otherwise
         */
        fun onItemClick(view: View, viewHolder: RecyclerView.ViewHolder, position: Int): Boolean
    }
}

