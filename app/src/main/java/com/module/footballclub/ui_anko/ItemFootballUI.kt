package com.module.footballclub.ui_anko

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.module.footballclub.R
import org.jetbrains.anko.*

class ItemFootballUI : AnkoComponent<ViewGroup> {

    companion object {
        val tvTitleId = 1
        val ivPhotoId = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            padding = dip(16)
            orientation = horizontalFadingEdgeLength

            imageView(R.mipmap.ic_launcher) {
                id = ivPhotoId
            }.lparams(width = 50, height = 50)

            textView {
                id = tvTitleId
                text = "Barcelona FC"
                textSize = 16f // <- it is sp, no worries
                textColor = Color.BLACK
            }.lparams(width = wrapContent, height = wrapContent) {
                leftMargin = dip(10)
            }

        }
    }
}
