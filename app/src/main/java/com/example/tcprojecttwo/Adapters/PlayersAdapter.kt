package com.example.tcprojecttwo.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tcprojecttwo.DataClasses.PlayersItem
import com.example.tcprojecttwo.R
import com.squareup.picasso.Picasso

class PlayersAdapter(val context: Context, val raidersList: List<PlayersItem>, private val onItemClick: (PlayersItem)-> Unit): RecyclerView.Adapter<PlayersAdapter.RaidersViewHolder>() {
    class RaidersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val nametv = itemView.findViewById<TextView>(R.id.nametv)
        val positiontv = itemView.findViewById<TextView>(R.id.positiontv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaidersViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.raider_cv, parent, false)
        return RaidersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RaidersViewHolder, position: Int) {
        holder.nametv.text = raidersList[position].name
        holder.positiontv.text = raidersList[position].position
        Picasso.get().load(raidersList[position].profile_image).into(holder.imageView)
        holder.itemView.setOnClickListener{
            onItemClick(raidersList[position])
        }

    }

    override fun getItemCount(): Int {
        return raidersList.size
    }

}