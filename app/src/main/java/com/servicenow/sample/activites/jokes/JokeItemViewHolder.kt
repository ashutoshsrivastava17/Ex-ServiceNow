package com.servicenow.sample.activites.jokes

import com.servicenow.sample.activites.base.BaseViewHolder
import com.servicenow.sample.databinding.ItemJokeBinding
import com.servicenow.sample.repository.database.models.Joke

class JokeItemViewHolder(binding: ItemJokeBinding?) : BaseViewHolder<Joke?, ItemJokeBinding?>(binding) {

    override fun bindData(position: Int, data: Joke?) {
        binding!!.data = data
        binding!!.executePendingBindings()
    }
}