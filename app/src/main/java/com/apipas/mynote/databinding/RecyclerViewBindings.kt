package com.apipas.mynote.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apipas.mynote.ui.base.BaseViewModel
import com.apipas.mynote.ui.base.adapter.SimpleRecyclerAdapter


@BindingAdapter(value = ["viewModel", "items", "layoutId"])
fun <T> bindItems(
    view: RecyclerView,
    vm: BaseViewModel?,
    items: ObservableArrayList<T>,
    layoutId: Int
) {
    if (view.adapter == null) {
        if (view.layoutManager == null) {
            view.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
            view.adapter = SimpleRecyclerAdapter(items, vm, layoutId)
        } else {
            (view.adapter as SimpleRecyclerAdapter<T>).setItems(items)
        }
    }
}