package com.servicenow.sample.activites.jokes

import android.app.Application
import androidx.lifecycle.LiveData
import com.novid.app.activities.base.BaseViewModel
import com.servicenow.sample.repository.backend.jokesAPI.models.JokesResponse
import com.servicenow.sample.repository.database.models.Joke
import com.servicenow.sample.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 1:32 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
class JokesViewModel(application: Application) : BaseViewModel<JokesNavigator?>(application), Callback<JokesResponse?> {

    val randomJokeFromDB: LiveData<MutableList<Joke>>
        get() = database.jokeDao.getJokes()

    fun getRandomJokeFromAPI(firstName: String?, lastName: String?) {
        showLoading("loading...")
        backendAPI.jokesAPI
                .getRandomJokes(firstName, lastName, listOf("nerdy"))
                .enqueue(this)
    }

    override fun onResponse(call: Call<JokesResponse?>, response: Response<JokesResponse?>) {
        hideLoading()
        if (response.isSuccessful) {
            postResponse(response.body())
        } else {
            navigator!!.onError(response.message())
        }
    }

    private fun postResponse(jokesResponse: JokesResponse?) {
        if (jokesResponse != null && "success".equals(jokesResponse.type, ignoreCase = true)) {
            val joke = jokesResponse.value
            if (joke != null) {
                AppExecutors().diskIO().execute { database.jokeDao.insert(joke) }
            }
        } else {
            navigator!!.onError("No Jokes Fetched")
        }
    }

    override fun onFailure(call: Call<JokesResponse?>, t: Throwable) {
        hideLoading()
        navigator!!.onError(t.message)
    }
}