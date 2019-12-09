package com.example.kadesubmisidua.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.favorite.FavoriteMatch
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter(
    val listFavorite : ArrayList<FavoriteMatch>,
    val listener : (FavoriteMatch) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
            itemView.item_favorite_tv_homename.text = favoriteMatch.eventHomeTeam
            itemView.item_favorite_tv_awayname.text = favoriteMatch.eventAwayTeam


            itemView.setOnClickListener { listener(favoriteMatch) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.item_favorite,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.bindItem(listFavorite[position],listener)
    }
}