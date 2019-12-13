package com.example.kadesubmisidua.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmisidua.R
import com.example.kadesubmisidua.model.classement.ClassementItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_classement.view.*

class ClassementAdapter(
    val listClassement : ArrayList<ClassementItem> = arrayListOf()
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
        return listClassement.size
    }

    override fun onBindViewHolder(holder: ClassementAdapter.ViewHolder, position: Int) {
        holder.itemView.item_classement_tvteam.text = listClassement.get(position).name
        holder.itemView.item_classement_tvlose.text = listClassement.get(position).loss.toString()
        holder.itemView.item_classement_tvwin.text = listClassement.get(position).win.toString()
        holder.itemView.item_classement_tvtotal.text = listClassement.get(position).total.toString()
    }
}