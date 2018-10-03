package com.module.footballclub.conn

class ResponseError(
        var protocol: String? = null,
        var code: Int = 0,
        var message: String? = null,
        var url: String? = null
)
