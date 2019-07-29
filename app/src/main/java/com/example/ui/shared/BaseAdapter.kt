package com.example.ui.shared

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val _list = mutableListOf<T>()

    val list get() = _list

    fun addAll(items: Collection<T>) {
        _list.addAll(items)
        notifyDataSetChanged()
    }

    fun addAll(items: Array<T>) {
        _list.addAll(items)
        notifyDataSetChanged()
    }

    fun update(items: Collection<T>) {
        _list.clear()
        _list.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    abstract override fun onBindViewHolder(holder: VH, position: Int)

}
