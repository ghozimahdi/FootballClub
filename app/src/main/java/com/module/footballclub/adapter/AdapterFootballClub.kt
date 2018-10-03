package com.module.footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.module.footballclub.R
import com.module.footballclub.model.EventsItem
import org.jetbrains.anko.*


class AdapterFootballClub(var context: Context, var list: MutableList<EventsItem> = arrayListOf()) : RecyclerView.Adapter<AdapterFootballClub.FootballViewHolder>() {

    private var listener: OnClickItems? = null

    fun setOnClickListener(onClickItems: OnClickItems) {
        this.listener = onClickItems
    }

    fun changeData(list: MutableList<EventsItem>) {
        this.list.clear()
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FootballViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        val football = list[position]
        holder.tvNama1.text = football.strHomeTeam
        holder.scor1.text = football.intHomeScore
        holder.scor2.text = football.intAwayScore
        holder.tvNama2.text = football.strAwayTeam
        holder.crt.text = football.dateEvent
        holder.itemView.setOnClickListener {
            listener?.onClick(football, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FootballViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var crt: TextView = itemView.findViewById(R.id.crt)
        var tvNama1: TextView = itemView.findViewById(R.id.tvNama1)
        var scor1: TextView = itemView.findViewById(R.id.scor1)
        var scor2: TextView = itemView.findViewById(R.id.scor2)
        var tvNama2: TextView = itemView.findViewById(R.id.tvNama2)
    }

    interface OnClickItems {
        fun onClick(footballClub: EventsItem, position: Int)
    }
}
