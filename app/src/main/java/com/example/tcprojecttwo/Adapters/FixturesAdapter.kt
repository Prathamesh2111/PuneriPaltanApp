package com.example.tcprojecttwo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tcprojecttwo.DataClasses.Fixture
import com.example.tcprojecttwo.R
import com.squareup.picasso.Picasso

class FixturesAdapter(private val fixture: List<Fixture>, private val onPrevClick: (Int) -> Unit,
                      private val onNextClick: (Int) -> Unit) : RecyclerView.Adapter<FixturesAdapter.FixtureViewHolder>() {
    class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val teamOne :ImageView = itemView.findViewById(R.id.imgTeam1Logo)
        val teamTwo :ImageView = itemView.findViewById(R.id.imgTeam2Logo)
        val tvDateTime : TextView = itemView.findViewById(R.id.tvDateTime)
        val prevButton = itemView.findViewById<ImageButton>(R.id.prevButton)
        val nextButton = itemView.findViewById<ImageButton>(R.id.nextButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fixture_cv, parent, false)
        return FixtureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        val fixture = fixture[position]

        Picasso.get().load(fixture.TeamOne).fit().centerInside().into(holder.teamOne)
        Picasso.get().load(fixture.TeamTwo).fit().centerInside().into(holder.teamTwo)
        holder.tvDateTime.text = fixture.DateTime

        // Handle prevButton click
        holder.prevButton.setOnClickListener {
            onPrevClick(position)
        }

        // Handle nextButton click
        holder.nextButton.setOnClickListener {
            onNextClick(position)
        }
    }


    override fun getItemCount(): Int {
        return fixture.size
    }
}