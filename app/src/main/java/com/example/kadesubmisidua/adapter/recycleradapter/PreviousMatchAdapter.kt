package com.example.kadesubmisidua.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.previousmatch.PreviousItem
import kotlinx.android.synthetic.main.item_previousmatch.view.*

class PreviousMatchAdapter(
    val listPreviousMatch: MutableList<PreviousItem>,
    val listener: (PreviousItem) -> Unit
) : RecyclerView.Adapter<PreviousMatchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(previousItem: PreviousItem, listener: (PreviousItem) -> Unit) {
            itemView.item_previousmatch_tv_homename.text = previousItem.strHomeTeam
            itemView.item_previousmatch_tv_awayname.text = previousItem.strAwayTeam

            itemView.item_previousmatch_tv_homescore.text = previousItem.intHomeScore
            itemView.item_previousmatch_tv_awayscore.text = previousItem.intAwayScore

            itemView.setOnClickListener { listener(previousItem) }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_previousmatch, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPreviousMatch.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listPreviousMatch[position], listener)
    }
}