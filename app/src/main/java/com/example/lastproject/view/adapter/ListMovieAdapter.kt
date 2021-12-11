package com.example.lastproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lastproject.R
import com.example.lastproject.databinding.FragmentListMovieBinding
import com.example.lastproject.databinding.MovieRvItemBinding
import com.example.lastproject.model.TvShow
import com.example.lastproject.view.fragments.ListMovieFragment
import com.squareup.picasso.Picasso

class ListMovieAdapter(
    private val context: ListMovieFragment,
    private val tvShows : List<TvShow>,
    private val onClickTvShow : ListMovieFragment,
    private val onItemSelect : (tvShow : TvShow) -> Unit
) :
    RecyclerView.Adapter<ListMovieAdapter.ViewHolder>(){

        private val allTvShow = tvShows

    inner class ViewHolder(private val  binding: MovieRvItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        val TvShowTitle = binding.idTVTitle
        val TvShowDate = binding.idTVDate
        val TvShowImage = binding.imageView
        val cardView = binding.cardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = allTvShow[position]
        holder.TvShowTitle.text = allTvShow[position].name
        holder.TvShowDate.text = "Publish Date : "+ allTvShow[position].start_date
        val picasso = Picasso.with(context.requireContext())
            .load(tvShow.image_thumbnail_path)
            .placeholder(R.drawable.image_placeholder)
            .centerCrop()
            .fit()
            .into(holder.TvShowImage)
        holder.cardView.setOnClickListener {
            onItemSelect(tvShow)
            onClickTvShow.onTvShowClick(tvShow)

        }
    }

    override fun getItemCount(): Int {
        return allTvShow.size
    }
}


 interface onClickTvShowInterface{
    fun onTvShowClick(tvShow: TvShow)
}
