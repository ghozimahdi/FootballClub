package com.module.footballclub.ui.schedules

import android.net.Uri
import com.google.gson.Gson
import com.module.footballclub.BuildConfig
import com.module.footballclub.InterfaceContract
import com.module.footballclub.OnResult
import com.module.footballclub.conn.MsgErrorBody
import com.module.footballclub.conn.ResponseError
import com.module.footballclub.model.EventsItem
import com.module.footballclub.model.MsgEventLeague

/**
 * Created by knalb on 02/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer Expert
 */
class PresenterSchedules(private val onView: InterfaceContract.OnSchedulesActivity) : InterfaceContract.OnPresenter {

    var lastData: MutableList<EventsItem> = ArrayList()
    var nextData: MutableList<EventsItem> = ArrayList()

    override fun getDataEventleague() {
        val urlLast = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath(BuildConfig.API)
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(BuildConfig.EVENTLAST)
                .build()
                .toString()

        val urlNext = Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath(BuildConfig.API)
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(BuildConfig.EVENTNEXT)
                .build()
                .toString()

        val paramsLast = HashMap<String, String>()
        paramsLast.put("id", "4328")
        val paramsNext = HashMap<String, String>()
        paramsNext.put("id", "4328")

        onView.doGetData(urlLast, paramsLast, object : OnResult {
            override fun onSuccess(response: String?) {
                val msg = Gson().fromJson(response, MsgEventLeague::class.java)
                lastData = msg.events as MutableList<EventsItem>

                onView.doGetData(urlNext, paramsNext, object : OnResult {
                    override fun onSuccess(response: String?) {
                        val msg = Gson().fromJson(response, MsgEventLeague::class.java)
                        nextData = msg.events as MutableList<EventsItem>
                        onView.doIfResultSuccess(lastData, nextData)

                    }

                    override fun onError(errorBody: String?, responseError: ResponseError?, msg: MsgErrorBody) {

                    }
                })
            }

            override fun onError(errorBody: String?, responseError: ResponseError?, msg: MsgErrorBody) {

            }
        })
    }
}
