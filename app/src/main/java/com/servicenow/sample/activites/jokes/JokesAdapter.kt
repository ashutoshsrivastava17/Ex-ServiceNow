package com.servicenow.sample.activites.jokes

import android.content.Context
import com.servicenow.sample.R
import com.servicenow.sample.activites.base.BaseRecyclerAdapter
import com.servicenow.sample.databinding.ItemJokeBinding
import com.servicenow.sample.repository.database.models.Joke

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 12:26 PM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
class JokesAdapter(context: Context) : BaseRecyclerAdapter<Joke?, ItemJokeBinding?, JokeItemVH?>(context) {

    fun removeTopItem(): Joke {
        val joke = getItems()[0]!!
        getItems().removeAt(0)
        notifyDataSetChanged()
        return joke
    }

    override fun getViewHolder(binding: ItemJokeBinding?): JokeItemVH {
        return JokeItemVH(binding)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_joke
    }
}