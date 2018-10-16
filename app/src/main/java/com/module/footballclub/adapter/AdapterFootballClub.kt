package com.module.footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.module.footballclub.R
import com.module.footballclub.model.EventsItem


class AdapterFootballClub(var context: Context, var list: MutableList<EventsItem> = arrayListOf(), private val listener: (EventsItem) -> Unit) : RecyclerView.Adapter<AdapterFootballClub.FootballViewHolder>() {


    fun changeData(list: MutableList<EventsItem>) {
        this.list.clear()
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FootballViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
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

        fun bindItem(football: EventsItem, listener: (EventsItem) -> Unit) {
            tvNama1.text = football.strHomeTeam
            scor1.text = football.intHomeScore
            scor2.text = football.intAwayScore
            tvNama2.text = football.strAwayTeam
            crt.text = football.dateEvent
            itemView.setOnClickListener {
                listener(football)
            }
        }
    }
}
