package com.go.it.planetapp.base



import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView


abstract class ObservableRecyclerViewAdapter<T, Holder: RecyclerView.ViewHolder>(
    private val items: ObservableList<T>
) : RecyclerView.Adapter<Holder>() {

  private var onItemClickListener: OnItemClickListener? = null

  init {
    items.addOnListChangedCallback(object: ObservableList.OnListChangedCallback<ObservableList<T>>() {
      override fun onChanged(sender: ObservableList<T>?) {
        notifyDataSetChanged()
      }

      override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyDataSetChanged()
      }

      override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
        notifyDataSetChanged()
      }

      override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyDataSetChanged()
      }

      override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
        notifyDataSetChanged()
      }
    })
  }

  override fun getItemCount(): Int = items.size

  fun getItem(i: Int): T {
    return items[i]
  }

  fun getItems(): ObservableList<T> {
    return items
  }

  fun getOnItemClickListener(): OnItemClickListener? {
    return onItemClickListener
  }

  fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
    this.onItemClickListener = onItemClickListener
  }

  interface OnItemClickListener {
    fun onItemClicked(item: Any)
  }
}