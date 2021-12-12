package com.example.lastproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastproject.databinding.EpisodeRvItemBinding
import com.example.lastproject.databinding.SeasonRvItemBinding
import com.example.lastproject.model.Episodes
import com.example.lastproject.model.TvShow
import com.example.lastproject.view.fragments.ListMovieFragment
import com.example.lastproject.view.fragments.TvShowDetailFragment

class SeasonAdapter  (
    private val context: TvShowDetailFragment,
    private val Seasons : Int,
    private val onClickSeadon : TvShowDetailFragment

    ) :
    RecyclerView.Adapter<SeasonAdapter.ViewHolder>(){
    inner class ViewHolder(private val  binding: SeasonRvItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        val SeasonNumber = binding.idTVSeasonNumber
        val cardView = binding.cardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SeasonRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var x=1;
        while (x<=Seasons){
            holder.SeasonNumber.text = x.toString()
            x++
        }
        holder.cardView.setOnClickListener {
            onClickSeadon.onSeasonClick(x)
        }
    }

    override fun getItemCount(): Int {
        return Seasons
    }
}
interface onClickSeasonInterface{
    fun onSeasonClick(season: Int)
}
