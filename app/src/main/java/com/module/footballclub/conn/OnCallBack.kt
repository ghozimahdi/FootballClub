package com.module.footballclub.conn

/**
 * Created by knalb on 23/07/18.
 */

interface OnCallBack {
    fun succses(response: String)

    fun error(errorBody: String, responseError: ResponseError)
}
