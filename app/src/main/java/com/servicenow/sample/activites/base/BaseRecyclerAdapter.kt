package com.servicenow.sample.activites.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 12:27 PM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
abstract class BaseRecyclerAdapter<BEAN, VB : ViewDataBinding?, VH : BaseViewHolder<BEAN, VB>?>(var context: Context) : RecyclerView.Adapter<VH>() {

    private var listener: OnItemClickListener<BEAN>? = null
    private var items: MutableList<BEAN>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: VB = DataBindingUtil.inflate<VB>(LayoutInflater.from(context),
                getLayoutId(viewType),
                parent,
                false)
        return getViewHolder(binding)
    }

    abstract fun getViewHolder(binding: VB): VH

    protected abstract fun getLayoutId(viewType: Int): Int

    override fun onBindViewHolder(holder: VH, position: Int) {
        val bean = items[position]
        holder!!.bindData(position, bean)
        holder.itemView.setOnClickListener {
            listener?.onItemClicked(position, bean)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItems(): MutableList<BEAN> {
        return items
    }

    fun addItems(newItems: Collection<BEAN>?) {
        this.items.clear()
        if (newItems != null) this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItem(newItem: BEAN?) {
        if (newItem != null) {
            items.add(newItem)
            notifyItemInserted(items.size - 1)
        }
    }

    fun addItemAtPosition(newItem: BEAN?, position: Int) {
        if (newItem != null && position != -1) {
            items.add(position, newItem)
            notifyItemInserted(position)
        }
    }

    fun removeItemAtPosition(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<BEAN>) {
        this.listener = listener
    }

    interface OnItemClickListener<BEAN> {
        fun onItemClicked(position: Int, bean: BEAN?)
    }

    init {
        this.items = ArrayList()
    }
}