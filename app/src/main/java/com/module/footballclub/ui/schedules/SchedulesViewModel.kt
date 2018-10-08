package com.module.footballclub.ui.schedules

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableBoolean
import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.android.databinding.library.baseAdapters.BR
import com.google.gson.Gson
import com.module.footballclub.BuildConfig
import com.module.footballclub.OnResult
import com.module.footballclub.adapter.SectionsPagerAdapter
import com.module.footballclub.conn.MsgErrorBody
import com.module.footballclub.conn.ResponseError
import com.module.footballclub.model.EventsItem
import com.module.footballclub.model.MsgEventLeague

/**
 * Created by knalb on 07/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
class SchedulesViewModel(private val schedulesResultCallback: SchedulesResultCallback) : BaseObservable() {
    private val adapter: SectionsPagerAdapter = schedulesResultCallback.getAdapter()
    private var lastData: MutableList<EventsItem> = ArrayList()
    private var nextData: MutableList<EventsItem> = ArrayList()
    val isLoading = ObservableBoolean()

    init {
        createViewPager()

        isLoading.set(true)
        doGetEvent()
    }

    fun onRefresh() {
        isLoading.set(true)
        doGetEvent()
    }

    private fun createViewPager() {
        notifyPropertyChanged(BR.pagerAdapter)
    }

    fun doGetEvent() {
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

        schedulesResultCallback.doRequest(urlLast, paramsLast, object : OnResult {
            override fun onSuccess(response: String?) {
                val msg = Gson().fromJson(response, MsgEventLeague::class.java)
                lastData = msg.events as MutableList<EventsItem>

                schedulesResultCallback.doRequest(urlNext, paramsNext, object : OnResult {
                    override fun onSuccess(response: String?) {
                        val msg = Gson().fromJson(response, MsgEventLeague::class.java)
                        nextData = msg.events as MutableList<EventsItem>
                        isLoading.set(false)
                        schedulesResultCallback.onSuccess(lastData, nextData)

                    }

                    override fun onError(errorBody: String?, responseError: ResponseError?, msg: MsgErrorBody) {

                    }
                })
            }

            override fun onError(errorBody: String?, responseError: ResponseError?, msg: MsgErrorBody) {

            }

        })
    }

    inner class ViewPagerSlideListener(private val pagerAdapter: SectionsPagerAdapter) : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            val fragment = pagerAdapter.getItem(position) as SchedulesFragment
            when (position) {
                0 -> {
                    if (fragment.adapterEvent?.itemCount == 0) {
                        fragment.changeData(lastData!!)
                    }
                }
                1 -> {
                    if (fragment.adapterEvent?.itemCount == 0) {
                        fragment.changeData(nextData!!)
                    }
                }
            }
        }
    }

    @Bindable
    fun getPageChange(): ViewPager.OnPageChangeListener {
        return ViewPagerSlideListener(adapter)
    }

    @Bindable
    fun getPagerAdapter(): PagerAdapter {
        return adapter
    }
}
