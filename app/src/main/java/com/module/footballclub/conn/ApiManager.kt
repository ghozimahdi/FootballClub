package com.module.footballclub.conn


import java.io.File

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * Created by knalb on 19/07/18.
 */

class ApiManager(private val apiService: ApiService) {

    fun doGet(url: String, query: Map<String, String>, apiObserver: ApiObserver) {
        apiService.doGet(url, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserver)
    }
}
