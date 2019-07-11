package fr.outadoc.quickhass.slideover

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class EditingModeCallback(private val viewModel: EntityGridViewModel) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or
                ItemTouchHelper.DOWN or
                ItemTouchHelper.START or
                ItemTouchHelper.END, 0
    ) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val adapter = recyclerView.adapter as ReorderableRecyclerViewAdapter

        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        adapter.moveItem(from, to)
        adapter.notifyItemMoved(from, to)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun onSelectedChanged(
        viewHolder: RecyclerView.ViewHolder?,
        actionState: Int
    ) {
        super.onSelectedChanged(viewHolder, actionState)

        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder?.itemView?.scale(1.1f, 10.0f)
        }
    }

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.scale(1.0f, 0.0f)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return viewModel.isEditingMode.value ?: false
    }

    private fun View.scale(scale: Float, elevation: Float) {
        this.animate()
            .translationZ(elevation)
            .scaleX(scale)
            .scaleY(scale)
            .setDuration(50)
            .start()
    }
}