package com.module.footballclub

import android.app.Application
import android.content.Context
import com.module.footballclub.conn.ApiManager
import com.module.footballclub.conn.ApiModule

class AppLoader : Application() {
    private var apiManager: ApiManager? = null

    companion object {
        lateinit var appContext: Context
        fun get(context: Context): AppLoader {
            return context.applicationContext as AppLoader
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    fun getApiManager(): ApiManager {
        if (apiManager == null) {
            val restAdapter = ApiModule().provideRestAdapterDinamic(ApiModule().provideOkHttpClient(), BuildConfig.BASE_URL)
            val apiService = ApiModule().provideApiService(restAdapter)
            apiManager = ApiManager(apiService)
        }
        return apiManager!!
    }
}

