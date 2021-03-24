package com.servicenow.sample.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.servicenow.sample.repository.database.models.Joke
import com.servicenow.sample.utils.layoutmanager.IDatabaseConstants

@Dao
interface JokeDao : IBaseDao<Joke> {

    @Transaction
    @Query("SELECT * FROM " + IDatabaseConstants.TABLE_JOKE + " ORDER BY id DESC")
    fun getJokes(): LiveData<MutableList<Joke>>


}