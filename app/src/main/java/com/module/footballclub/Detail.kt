package com.module.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import com.module.footballclub.adapter.AdapterFootballClub
import com.module.footballclub.model.FootballClub
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailUI(intent.extras.getParcelable("data")).setContentView(this)
    }

    class DetailUI(var footballClub: FootballClub) : AnkoComponent<Detail> {
        override fun createView(ui: AnkoContext<Detail>): View = with(ui) {
            return verticalLayout {
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(16)
                imageView(footballClub.image!!) {
                    id = R.id.img_football
                }.lparams {
                    height = dip(80)
                    width = dip(80)
                }

                textView {
                    text = footballClub.name
                    id = R.id.name_football
                    textSize = 20f
                }.lparams {
                    topMargin = dip(10)
                }

                textView {
                    text = footballClub.description
                    id = R.id.name_football
                    textSize = 16f
                }.lparams {
                    topMargin = dip(16)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }
    }
}
