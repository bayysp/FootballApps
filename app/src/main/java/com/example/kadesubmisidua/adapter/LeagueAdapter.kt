package com.example.kadesubmisidua.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.league.LeagueItem
import com.example.kadesubmisidua.view.activity.DetailLeagueActivity
import com.example.kadesubmisidua.view.activity.MatchActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter(
    val context : Context,
    val listLeague :  ArrayList<LeagueItem> = arrayListOf()
) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listLeague.size
    }

    override fun onBindViewHolder(holder: LeagueAdapter.ViewHolder, position: Int) {
        holder.itemView.item_league_tv_leaguetitle.text = listLeague.get(position).leagueTitle
        Picasso.get().load(listLeague.get(position).leagueThumb).into(holder.itemView.item_league_ivleague)

        holder.itemView.item_league_btn_detailleague.setOnClickListener {
            val intent = Intent(context,DetailLeagueActivity::class.java)
            intent.putExtra("id_league",listLeague.get(position).leagueId)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        holder.itemView.item_league_btn_showmatch.setOnClickListener {
            val intent = Intent(context,MatchActivity::class.java)
            intent.putExtra("id_league",listLeague.get(position).leagueId)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(intent)
        }
    }
}