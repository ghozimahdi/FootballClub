package com.module.footballclub.conn

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Created by knalb on 19/07/18.
 */

interface ApiService {
    @GET
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun doGet(@Url url: String, @QueryMap(encoded = false) options: Map<String, String>): Observable<String>
}
