package com.servicenow.sample.repository

import com.servicenow.sample.repository.backend.IBackendAPI
import com.servicenow.sample.repository.database.IDatabase
import com.servicenow.sample.repository.preferences.ISharedPref

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 1:57 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
interface IDataRepository {

    fun getBackendAPI(): IBackendAPI
    fun getDatabase(): IDatabase
    fun getSharedPref(): ISharedPref


    fun clearAll() {
        getSharedPref().clear()
    }

}