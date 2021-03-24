package com.servicenow.sample.repository.database.dao

import androidx.room.*

@Dao
interface IBaseDao<E> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: E): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<E>?): LongArray

    @Update
    fun update(entitiy: E): Int

    @Delete
    fun delete(entitiy: E): Int
}