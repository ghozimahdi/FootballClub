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
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.module.footballclub.R
import com.module.footballclub.model.FootballClub
import org.jetbrains.anko.*

class AdapterFootballClub(var context: Context, var list: MutableList<FootballClub> = arrayListOf()) : RecyclerView.Adapter<AdapterFootballClub.FootballViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballViewHolder {
        return FootballViewHolder(ItemList().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        val football = list[position]
        holder.tvTitle.text = football.name
        displayImageRound(context, holder.ivPhotoId, football.image!!)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FootballViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView
        var ivPhotoId: ImageView

        init {
            tvTitle = itemView.find(R.id.img_football)
            ivPhotoId = itemView.find(R.id.name_football)
        }

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

    class ItemList : AnkoComponent<ViewGroup> {

        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.img_football
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.name_football
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }

                }
            }
        }


    }
}
