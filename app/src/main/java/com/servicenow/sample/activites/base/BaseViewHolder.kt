package com.servicenow.sample.activites.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 12:27 PM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
abstract class BaseViewHolder<BEAN, VDB : ViewDataBinding?> : RecyclerView.ViewHolder {

    protected var binding: VDB

    constructor(binding: VDB) : super(binding!!.root) {
        this.binding = binding
    }

    abstract fun bindData(position: Int, data: BEAN)

}
