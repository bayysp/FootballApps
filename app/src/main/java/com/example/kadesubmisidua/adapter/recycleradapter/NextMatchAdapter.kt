package com.example.kadesubmisidua.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.nextmatch.NextItem
import kotlinx.android.synthetic.main.item_nextmatch.view.*

class NextMatchAdapter(
    val listNextMatch: MutableList<NextItem>,
    val listener: (NextItem) -> Unit
) :
    RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(nextItem: NextItem, listener: (NextItem) -> Unit){
            itemView.item_nextmatch_tv_homename.text = nextItem.strHomeTeam
            itemView.item_nextmatch_tv_awayname.text = nextItem.strAwayTeam
            itemView.setOnClickListener { listener(nextItem) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nextmatch,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNextMatch.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listNextMatch[position],listener)
    }
}