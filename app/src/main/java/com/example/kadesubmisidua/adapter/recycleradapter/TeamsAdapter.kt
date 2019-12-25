package com.example.kadesubmisidua.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.team.TeamsItem
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_teams.view.*

class TeamsAdapter(
    val listTeams: ArrayList<TeamsItem>,
    val listener: (TeamsItem) -> Unit
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(teamsItem: TeamsItem, listener: (TeamsItem) -> Unit) {
            Picasso.get().load(teamsItem.strTeamBadge).into(itemView.itemteams_iv_thumb)
            itemView.itemteams_tv_teamname.text = teamsItem.strTeam

            itemView.setOnClickListener { listener(teamsItem) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teams,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTeams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listTeams[position], listener)
    }

}