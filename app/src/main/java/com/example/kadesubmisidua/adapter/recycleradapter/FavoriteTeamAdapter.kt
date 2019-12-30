package com.example.kadesubmisidua.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.favorite.FavoriteTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*

class FavoriteTeamAdapter(
    val listTeam : ArrayList<FavoriteTeam>,
    val listener : (FavoriteTeam) -> Unit
) : RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
            itemView.itemteams_tv_teamname.text = favoriteTeam.teamName
            Picasso.get().load(favoriteTeam.teamThumb).into(itemView.itemteams_iv_thumb)

            itemView.setOnClickListener { listener(favoriteTeam) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).
            inflate(R.layout.item_teams,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTeam.size
    }

    override fun onBindViewHolder(holder: FavoriteTeamAdapter.ViewHolder, position: Int) {
        holder.bindItem(listTeam[position],listener)
    }
}