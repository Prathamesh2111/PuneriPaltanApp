package com.example.tcprojecttwo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tcprojecttwo.Adapters.MatchImagesAdapter.MatchImageViewHolder
import com.example.tcprojecttwo.DataClasses.GalleryShowItem
import com.example.tcprojecttwo.R
import com.squareup.picasso.Picasso

class GalleryShowAdapter(var galleryShowList: List<GalleryShowItem>) : RecyclerView.Adapter<GalleryShowAdapter.GalleryShowViewHolder>() {
    class GalleryShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val seasonnum = itemView.findViewById<TextView>(R.id.season_no)
        val gamename = itemView.findViewById<TextView>(R.id.game_name)
        val mainimage = itemView.findViewById<ImageView>(R.id.main_image)
        val imagesRV = itemView.findViewById<RecyclerView>(R.id.imagesRV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryShowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.gallery_show, parent, false)
        return GalleryShowViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return galleryShowList.size
    }

    override fun onBindViewHolder(holder: GalleryShowViewHolder, position: Int) {
        holder.seasonnum.text = galleryShowList[position].cat_name
        holder.gamename.text = galleryShowList[position].name
        Picasso.get().load(galleryShowList[position].main_image).into(holder.mainimage)
        val imageAdapter = MatchImagesAdapter(galleryShowList[position].match_images)
        holder.imagesRV.adapter = imageAdapter
        holder.imagesRV.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
    }
    fun updateData(newGalleryShowList: List<GalleryShowItem>) {
        galleryShowList = newGalleryShowList
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}