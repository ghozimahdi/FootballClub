package com.module.footballclub.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.module.footballclub.R
import com.module.footballclub.model.FootballClub
import org.jetbrains.anko.*
import android.media.MediaScannerConnection
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import java.io.FileOutputStream
import java.io.IOException


class AdapterFootballClub(var context: Context, var list: MutableList<FootballClub> = arrayListOf()) : RecyclerView.Adapter<AdapterFootballClub.FootballViewHolder>() {

    private var listener: OnClickItems? = null

    fun setOnClickListener(onClickItems: OnClickItems) {
        this.listener = onClickItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballViewHolder {
        return FootballViewHolder(ItemList().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        val football = list[position]
        holder.tvTitle.text = football.name
        Glide.with(context).load(football.image).into(holder.ivPhotoId)
        holder.itemView.setOnClickListener {
            listener!!.onClick(football, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FootballViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var ivPhotoId: ImageView

        init {
            tvTitle = itemView.find(R.id.name_football)
            ivPhotoId = itemView.find(R.id.img_football)
        }

    }

    interface OnClickItems {
        fun onClick(footballClub: FootballClub, position: Int)
    }

    fun displayImageRound(ctx: Context, img: ImageView, @DrawableRes drawable: Int) {
        try {
            Glide.with(ctx).load(drawable).asBitmap().centerCrop().into(object : BitmapImageViewTarget(img) {
                override fun setResource(resource: Bitmap) {
                    val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(ctx.resources, resource)
                    circularBitmapDrawable.isCircular = true
                    img.setImageDrawable(circularBitmapDrawable)
                }
            })
        } catch (e: Exception) {
        }

    }

    fun displayImageOriginal(ctx: Context, img: ImageView, url: String) {
        try {
            Glide.with(ctx).load(url)
                    .crossFade()
                    .override(600, 200)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img)
        } catch (e: Exception) {
        }

    }

    class ItemList : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.img_football
                    }.lparams {
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.name_football
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }
    }
}
