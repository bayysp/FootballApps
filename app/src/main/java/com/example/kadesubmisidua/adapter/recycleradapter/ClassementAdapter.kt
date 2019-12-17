package com.example.kadesubmisidua.adapter.recycleradapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.api.ApiRepository
import com.example.kadesubmisidua.api.SportDBApi
import com.example.kadesubmisidua.model.classement.ClassementItem
import com.example.kadesubmisidua.model.team.TeamsItem
import com.example.kadesubmisidua.model.team.TeamsResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_previous.*
import kotlinx.android.synthetic.main.item_classement.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ClassementAdapter(
    val listClassement : ArrayList<ClassementItem>,
    val listTeamId : ArrayList<String>
) : RecyclerView.Adapter<ClassementAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassementAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_classement,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("ClassementAdapter", "listclassement : "+listClassement.size+" | listteamid : "+listTeamId.size)
        return listClassement.size
    }

    override fun onBindViewHolder(holder: ClassementAdapter.ViewHolder, position: Int) {
        holder.itemView.item_classement_tvteam.text = listClassement.get(position).name
        holder.itemView.item_classement_tvlose.text = listClassement.get(position).loss.toString()
        holder.itemView.item_classement_tvwin.text = listClassement.get(position).win.toString()
        holder.itemView.item_classement_tvtotal.text = listClassement.get(position).total.toString()
//        Picasso.get().load(getTeamThumb(listTeamId.toString())).into(holder.itemView.item_classement_ivteam)
    }

//    private fun getTeamThumb(idTeam : String) : String{
//        val gson = Gson()
//        val apiRepository = ApiRepository()
//        var idTeam : String = ""
//        try {
//            doAsync {
//                val data = gson.fromJson(
//                    apiRepository.doRequest(SportDBApi.getTeam("lookupteam.php",idTeam)),
//                    TeamsResponse::class.java
//                )
//            }
//
//
//        }
//
//        return teamThumbUrl
//    }
}