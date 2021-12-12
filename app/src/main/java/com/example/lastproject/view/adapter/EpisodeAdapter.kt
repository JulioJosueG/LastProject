package com.example.lastproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastproject.R
import com.example.lastproject.databinding.EpisodeRvItemBinding
import com.example.lastproject.databinding.MovieRvItemBinding
import com.example.lastproject.model.Episodes
import com.example.lastproject.model.TvShow
import com.example.lastproject.view.fragments.ListMovieFragment
import com.example.lastproject.view.fragments.TvShowDetailFragment
import com.squareup.picasso.Picasso

class EpisodeAdapter (
    private val context: TvShowDetailFragment,
    private val Episodes : List<Episodes>,
) :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>(){
    inner class ViewHolder(private val  binding: EpisodeRvItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        val EpisodeName = binding.idTvEpisodeName
        val EpisodeNumber = binding.idTvEpisodeNumber
        val EpisodeDate = binding.idTvEpisodeAirDate
        val cardView = binding.cardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EpisodeRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Episode = Episodes[position]

            holder.EpisodeName.text = Episodes[position].name
            holder.EpisodeNumber.text = Episodes[position].episode.toString()
            holder.EpisodeDate.text = "Air Date: "+ Episodes[position].air_date

    }

    override fun getItemCount(): Int {
        return Episodes.size
    }
}
