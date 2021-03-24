package com.servicenow.sample.repository

import android.app.Application
import com.novid.app.dataRepository.backend.BackendAPI
import com.novid.app.dataRepository.preferences.SharedPref
import com.servicenow.sample.repository.backend.IBackendAPI
import com.servicenow.sample.repository.database.IDatabase
import com.servicenow.sample.repository.database.LocalDatabase
import com.servicenow.sample.repository.preferences.ISharedPref

/**
 * Created by Ashutosh Srivastava on 24-Mar-2021 1:57 AM.
 * Project : Ex-ServiceNow
 * Copyright (c) 2021  All rights reserved.
 */
class DataRepository : IDataRepository {

    private lateinit var sharedPref: ISharedPref
    private lateinit var database: IDatabase
    private lateinit var backendAPI: IBackendAPI

    private constructor() {}
    private constructor(iBackendAPI: IBackendAPI, iDatabase: IDatabase, iSharedPref: ISharedPref) {
        backendAPI = iBackendAPI
        database = iDatabase
        sharedPref = iSharedPref
    }

    private constructor(application: Application) : this(BackendAPI.getInstance(application),
            LocalDatabase.getInstance(application),
            SharedPref.getInstance(application))

    companion object {
        private var mDataRepository: DataRepository? = null

        @JvmStatic
        fun getInstance(application: Application): IDataRepository {
            if (mDataRepository == null) mDataRepository = DataRepository(application)
            return mDataRepository!!
        }
    }

    override fun getBackendAPI(): IBackendAPI {
        return backendAPI
    }

    override fun getDatabase(): IDatabase {
        return database
    }

    override fun getSharedPref(): ISharedPref {
        return sharedPref
    }
}