package com.novid.app.dataRepository.backend

import android.app.Application
import androidx.annotation.NonNull
import com.servicenow.sample.BuildConfig
import com.servicenow.sample.repository.backend.IBackendAPI
import com.servicenow.sample.repository.backend.jokesAPI.JokesAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BackendAPI private constructor(private val application: Application) : IBackendAPI {


    override fun getJokesAPI(): JokesAPI {
        return this.mRetrofit!!.create(JokesAPI::class.java)
    }

    companion object {
        private var mBackendAPI: IBackendAPI? = null
        fun getInstance(application: Application): IBackendAPI {
            if (mBackendAPI == null) mBackendAPI = BackendAPI(application)
            return mBackendAPI!!
        }
    }

    private var mRetrofit: Retrofit? = null
        get() {
            if (field == null) {
                mRetrofit = Retrofit.Builder()
                        .baseUrl("https://api.icndb.com/")
//                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return field
        }

    private val okHttpClient: OkHttpClient
        @NonNull
        get() {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(httpLoggingInterceptor)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.retryOnConnectionFailure(true)
            return builder.build()
        }

    private val httpLoggingInterceptor: HttpLoggingInterceptor
        @NonNull
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            return httpLoggingInterceptor
        }


}