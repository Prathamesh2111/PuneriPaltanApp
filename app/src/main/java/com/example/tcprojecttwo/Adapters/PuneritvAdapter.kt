package com.example.tcprojecttwo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tcprojecttwo.DataClasses.PuneriTv
import com.example.tcprojecttwo.R

class PuneritvAdapter(private val puneritvData: List<PuneriTv> , private val onItemClick: (PuneriTv)-> Unit): RecyclerView.Adapter<PuneritvAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stringTitle = itemView.findViewById<TextView>(R.id.textView1)
        val date = itemView.findViewById<TextView>(R.id.textView2)
        val image = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.puneritv_cv, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = puneritvData[position]
        holder.stringTitle.text = item.string1
        holder.date.text = item.string2
        holder.image.setImageResource(item.imageResId)
        holder.itemView.setOnClickListener{
            onItemClick(puneritvData[position])
        }
    }

    override fun getItemCount(): Int {
        return puneritvData.size
    }
}