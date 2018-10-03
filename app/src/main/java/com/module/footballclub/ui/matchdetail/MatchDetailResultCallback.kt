package com.module.footballclub.ui.matchdetail

import com.module.footballclub.model.EventsItem

/**
 * Created by knalb on 03/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
interface MatchDetailResultCallback {
    fun onSuccess()
    fun onError()
    fun loadEventItem(): EventsItem
}