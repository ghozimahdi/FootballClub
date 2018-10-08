package com.module.footballclub.ui.schedules

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.module.footballclub.AppLoader
import com.module.footballclub.OnResult
import com.module.footballclub.R
import com.module.footballclub.adapter.SectionsPagerAdapter
import com.module.footballclub.conn.ApiObserver
import com.module.footballclub.conn.OnCallBack
import com.module.footballclub.conn.ResponseError
import com.module.footballclub.databinding.SchedulesBinding
import com.module.footballclub.model.EventsItem


class SchedulesActivity : AppCompatActivity(), SchedulesResultCallback {
    private val fragmentPrev = SchedulesFragment.newInstance(0)
    private val fragmentNext = SchedulesFragment.newInstance(1)

    private lateinit var binding: SchedulesBinding
    private lateinit var viewModel: SchedulesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.schedules)
        viewModel = SchedulesViewModel(this)
        binding.schedulesViewModel = viewModel
        binding.swlayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)
    }

    override fun getAdapter(): SectionsPagerAdapter {
        var adapter = SectionsPagerAdapter(supportFragmentManager)
        adapter.addFragment(fragmentPrev, "Prev Macth")
        adapter.addFragment(fragmentNext, "Next Match")
        return adapter
    }

    override fun doRequest(url: String, query: Map<String, String>, result: OnResult) {
        AppLoader.get(this@SchedulesActivity)
                .getApiManager()
                .doGet(url, query, ApiObserver(object : OnCallBack {
                    override fun succses(response: String) {
                        result.onSuccess(response)
                    }

                    override fun error(errorBody: String, responseError: ResponseError) {

                    }

                }))
    }

    override fun onSuccess(lastData: MutableList<EventsItem>, nextData: MutableList<EventsItem>) {
        fragmentPrev.changeData(lastData)
        fragmentNext.changeData(nextData)
    }

    override fun onError(response: String) {

    }
}