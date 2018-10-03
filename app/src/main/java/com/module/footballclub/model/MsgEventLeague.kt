package com.module.footballclub.model

import android.os.Parcelable
import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Generated("com.robohorse.robopojogenerator")
data class MsgEventLeague (
        @field:SerializedName("events")
        val events: List<EventsItem?>? = null
) : Parcelable