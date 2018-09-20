package com.module.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ListAdapter
import com.module.footballclub.adapter.AdapterFootballClub
import com.module.footballclub.model.FootballClub
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<FootballClub> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        MainActivityUI(AdapterFootballClub(this@MainActivity, items)).setContentView(this)
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        items.clear()
        for (i in name.indices) {
            items.add(FootballClub(name[i],
                    image.getResourceId(i, 0)))
        }

        //Recycle the typed array
        image.recycle()
    }

    class MainActivityUI(var listAdapter: AdapterFootballClub) : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            return frameLayout {
                // LIST
                val list = recyclerView {
                    val orientation = LinearLayoutManager.VERTICAL
                    layoutManager = LinearLayoutManager(context, orientation, true)
                    overScrollMode = View.OVER_SCROLL_NEVER
                    adapter = listAdapter
                    adapter.registerAdapterDataObserver(
                            object : RecyclerView.AdapterDataObserver() {
                                override fun onItemRangeInserted(start: Int, count: Int) {

                                }

                                override fun onItemRangeRemoved(start: Int, count: Int) {

                                }
                            })

                }.lparams(width = matchParent, height = wrapContent) {

                }
            }.apply {
                layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                        .apply {
                            /*leftMargin = dip(16)
                            rightMargin = dip(16)
                            bottomMargin = dip(16)*/
                        }
            }
        }
    }
}
