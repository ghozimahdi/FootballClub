package com.module.footballclub.ui.schedules

import com.module.footballclub.OnResult
import com.module.footballclub.adapter.SectionsPagerAdapter
import com.module.footballclub.model.EventsItem

/**
 * Created by knalb on 07/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
interface SchedulesResultCallback {
    fun onSuccess(lastData: MutableList<EventsItem>, nextData: MutableList<EventsItem>)
    fun onError(response: String)
    fun getAdapter(): SectionsPagerAdapter
    fun doRequest(url: String, query: Map<String, String>, result: OnResult)
}