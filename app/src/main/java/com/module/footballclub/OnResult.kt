package com.module.footballclub

import com.module.footballclub.conn.MsgErrorBody
import com.module.footballclub.conn.ResponseError

/**
 * Created by knalb on 07/09/18.
 */

interface OnResult {
    fun onSuccess(response: String?)
    fun onError(errorBody: String?, responseError: ResponseError?, msg: MsgErrorBody)
}