package com.module.footballclub.conn

import com.google.gson.annotations.SerializedName

import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class MsgErrorBody(

        @SerializedName("path")
        var path: String? = null,

        @SerializedName("error")
        var error: String? = null,

        @SerializedName("message")
        var message: String? = null,

        @SerializedName("timestamp")
        var timestamp: String? = null,

        @SerializedName("status")
        var status: Int = 0)