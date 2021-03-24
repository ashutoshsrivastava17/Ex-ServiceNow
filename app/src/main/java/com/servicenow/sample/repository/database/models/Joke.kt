package com.servicenow.sample.repository.database.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.servicenow.sample.utils.IDatabaseConstants
import org.jetbrains.annotations.NotNull

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 2:38 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
@Entity(tableName = IDatabaseConstants.TABLE_JOKE)
class Joke {

    @PrimaryKey
    @NotNull
    var id: Int = 0

    var joke: String? = null

    @Ignore
    var categories: List<String>? = null
}