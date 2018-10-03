package com.module.footballclub

import android.net.Uri
import android.support.v4.app.Fragment
import com.module.footballclub.model.EventsItem
import okhttp3.RequestBody

/**
 * Created by knalb on 28/09/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer Expert
 */
interface InterfaceContract {
    interface OnFragment {

    }

    interface OnActivity {

    }

    interface OnSchedulesActivity {
        fun doGetData(url: String, query: Map<String, String>, result: OnResult)
        fun doIfResultSuccess(lastData: MutableList<EventsItem>, nextData: MutableList<EventsItem>)
    }

    interface OnPresenter {
        fun getDataEventleague()
    }
}
