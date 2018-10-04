package com.module.footballclub.ui.schedules

import android.os.Bundle
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import com.module.footballclub.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.schedules.*
import com.module.footballclub.*
import com.module.footballclub.adapter.SectionsPagerAdapter
import com.module.footballclub.conn.ApiObserver
import com.module.footballclub.conn.OnCallBack
import com.module.footballclub.conn.ResponseError
import com.module.footballclub.model.EventsItem


class Schedules : AppCompatActivity(), InterfaceContract.OnSchedulesActivity {
    override fun doIfResultSuccess(lastData: MutableList<EventsItem>, nextData: MutableList<EventsItem>) {
        swlayout.isRefreshing = false
        fragmentPrev.changeData(lastData)
        fragmentNext.changeData(nextData)
    }

    override fun doGetData(url: String, query: Map<String, String>, result: OnResult) {
        AppLoader.get(this@Schedules)
                .getApiManager()
                .doGet(url, query, ApiObserver(object : OnCallBack {
                    override fun succses(response: String) {
                        result.onSuccess(response)
                    }

                    override fun error(errorBody: String, responseError: ResponseError) {

                    }

                }))
    }

    private var adapterTab: SectionsPagerAdapter? = null
    var fragmentPrev = SchedulesFragment.newInstance(0)
    var fragmentNext = SchedulesFragment.newInstance(1)
    var presenterSchedules: PresenterSchedules? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedules)

        presenterSchedules = PresenterSchedules(this)

        adapterTab = SectionsPagerAdapter(supportFragmentManager)
        adapterTab?.addFragment(fragmentPrev, "Prev Macth")
        adapterTab?.addFragment(fragmentNext, "Next Match")
        main_container.adapter = adapterTab
        main_container.addOnPageChangeListener(ViewPagerSlideListener(adapterTab!!))

        tabs.setupWithViewPager(main_container)

        swlayout.post {
            presenterSchedules?.getDataEventleague()
        }

        swlayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)
        swlayout.setOnRefreshListener {
            presenterSchedules?.getDataEventleague()
        }
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
                        fragment.changeData(presenterSchedules?.lastData!!)
                    }
                }
                1 -> {
                    if (fragment.adapterEvent?.itemCount == 0) {
                        fragment.changeData(presenterSchedules?.nextData!!)
                    }
                }
            }
        }
    }
}