package com.example.kadesubmisidua.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.searchmatch.SearchItem
import kotlinx.android.synthetic.main.item_nextmatch.view.*

class SearchAdapter(
    val searchMatch: MutableList<SearchItem>,
    val listener: (SearchItem) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(searchItem: SearchItem, listener: (SearchItem) -> Unit) {

            if (searchMatch.size != 0 && searchItem.strSport == "Soccer") {

                if (searchItem.intAwayScore != null && searchItem.intHomeScore != null) {
                    itemView.item_nextmatch_tv_homescore.text = searchItem.intHomeScore.toString()
                    itemView.item_nextmatch_tv_awayscore.text = searchItem.intAwayScore.toString()
                }
                itemView.item_nextmatch_tv_homename.text = searchItem.strHomeTeam
                itemView.item_nextmatch_tv_awayname.text = searchItem.strAwayTeam
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_nextmatch, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchMatch.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bindItem(searchMatch[position], listener)
    }
}