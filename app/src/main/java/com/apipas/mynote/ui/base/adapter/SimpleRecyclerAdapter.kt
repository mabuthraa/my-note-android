package com.apipas.mynote.ui.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding

import androidx.recyclerview.widget.RecyclerView
import com.apipas.mynote.BR
import com.apipas.mynote.ui.base.BaseViewModel

class SimpleRecyclerAdapter<T>(
    items: ObservableList<T>,
    private val viewModel: BaseViewModel?,
    @LayoutRes private val layoutId: Int
) : RecyclerView.Adapter<SimpleRecyclerAdapter<T>.BaseMvvmRecyclerViewHolder<T>>() {

    private var items: ObservableList<T>? = items

    private var onListChangedCallback: ObservableList.OnListChangedCallback<ObservableList<T>>? = null

    init {
        initOnListChangedListener()
    }

    private fun initOnListChangedListener() {
        onListChangedCallback = object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
            override fun onChanged(sender: ObservableList<T>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeMoved(
                sender: ObservableList<T>,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
                notifyItemRangeRemoved(positionStart, itemCount)
            }
        }
        items!!.addOnListChangedCallback(onListChangedCallback)
    }

    private fun getViewHolderBinding(parent: ViewGroup, @LayoutRes itemLayoutId: Int): ViewDataBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), itemLayoutId, parent, false)
    }

    override fun onBindViewHolder(holder: SimpleRecyclerAdapter<T>.BaseMvvmRecyclerViewHolder<T>, position: Int) {
        val item = items!![position]
        holder.bind(item, holder.binder)
        holder.binder!!.executePendingBindings()
    }

    fun getItem(position: Int): T {
        return items!![position]
    }

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    fun getItems(): List<T>? {
        return items
    }

    fun setItems(items: ObservableList<T>?) {
        if (items != null && items == this.items) {
            //notifyDataSetChanged();
        } else {
            this.items = items
            initOnListChangedListener()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMvvmRecyclerViewHolder<T> {
        return BaseMvvmRecyclerViewHolder(getViewHolderBinding(parent, layoutId))
    }

    inner class BaseMvvmRecyclerViewHolder<T>(v: ViewDataBinding) : RecyclerView.ViewHolder(v.root) {

        val binder: ViewDataBinding? = DataBindingUtil.bind(v.root)

        fun bind(item: T, binder: ViewDataBinding?) {
            binder!!.setVariable(BR.vm, viewModel)
            binder.setVariable(BR.item, item)
        }
    }
}