package com.example.tcprojecttwo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tcprojecttwo.DataClasses.GalleryData
import com.example.tcprojecttwo.DataClasses.PlayersItem
import com.example.tcprojecttwo.R

class GalleryAdapter(val galleryList: List<GalleryData>, private val onItemClick: (GalleryData)-> Unit) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val seasonLabel = itemView.findViewById<TextView>(R.id.seasonLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gallery_season_cv, parent, false)
        return GalleryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.seasonLabel.text = galleryList[position].title
        holder.itemView.setOnClickListener {
            onItemClick(galleryList[position])
        }
    }

}