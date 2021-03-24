package com.novid.app.activities.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.servicenow.sample.repository.backend.IBackendAPI
import com.servicenow.sample.repository.database.IDatabase
import com.servicenow.sample.repository.preferences.ISharedPref
import com.servicenow.sample.repository.DataRepository

open class BaseViewModel<NAV : BaseNavigator?>(application: Application) : AndroidViewModel(application) {

    protected var sharedPref: ISharedPref
    protected var database: IDatabase
    protected var backendAPI: IBackendAPI
    private var loaderLiveData: MutableLiveData<Boolean>

    init {
        val mDataRepository = DataRepository.getInstance(application)
        this.backendAPI = mDataRepository.getBackendAPI()
        this.database = mDataRepository.getDatabase()
        this.sharedPref = mDataRepository.getSharedPref()
        this.loaderLiveData = MutableLiveData()
    }

    var loaderMessage: String? = null
        get() = field

    var navigator: NAV? = null

    open fun showLoading(message: String) {
        this.loaderMessage = message
        this.loaderLiveData.postValue(true)
    }

    open fun observerLoader(): LiveData<Boolean> {
        return loaderLiveData
    }

    open fun hideLoading() {
        this.loaderLiveData.postValue(false)
    }


}