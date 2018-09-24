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
        val img_url = resources.getStringArray(R.array.url_img)
        items.clear()
        for (i in name.indices) {
            items.add(FootballClub(name[i],
                    image.getResourceId(i, 0), img_url[i], resources.getString(R.string.long_lorem_ipsum)))
        }

        //Recycle the typed array
        image.recycle()
    }

    class MainActivityUI(var listAdapter: AdapterFootballClub) : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            return verticalLayout {
                recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = listAdapter
                    listAdapter.setOnClickListener(object : AdapterFootballClub.OnClickItems {
                        override fun onClick(footballClub: FootballClub, position: Int) {
                            startActivity<Detail>("data" to footballClub)
                        }
                    })
                }
            }
        }
    }
}
