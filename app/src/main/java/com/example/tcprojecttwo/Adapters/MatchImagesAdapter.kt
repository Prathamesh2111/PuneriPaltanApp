package com.example.tcprojecttwo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tcprojecttwo.R
import com.squareup.picasso.Picasso

class MatchImagesAdapter(private val matchImages: List<String>) : RecyclerView.Adapter<MatchImagesAdapter.MatchImageViewHolder>() {
    class MatchImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.match_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return MatchImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matchImages.size
    }

    override fun onBindViewHolder(holder: MatchImageViewHolder, position: Int) {
        Picasso.get().load(matchImages[position]).into(holder.imageView)
    }
}