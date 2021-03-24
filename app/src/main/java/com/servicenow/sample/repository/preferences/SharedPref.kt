package com.novid.app.dataRepository.preferences

import android.content.Context
import android.content.SharedPreferences
import com.servicenow.sample.BuildConfig
import com.servicenow.sample.repository.preferences.ISharedPref

class SharedPref private constructor(context: Context) : ISharedPref {

    private val mSharedPreferences: SharedPreferences

    init {
        mSharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    override fun getSharedPreferneces(): SharedPreferences {
        return mSharedPreferences
    }

    companion object {

        private var mSharedPref: ISharedPref? = null

        /**
         * returns single instance for [SharedPref]
         *
         * @param context
         * @return ISharedPref
         */
        fun getInstance(context: Context): ISharedPref {
            if (mSharedPref == null) mSharedPref = SharedPref(context.applicationContext)
            return mSharedPref!!
        }
    }


}