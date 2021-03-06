package com.example.lastproject.view.fragments

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastproject.R
import com.example.lastproject.databinding.FragmentTvShowDetailBinding
import com.example.lastproject.model.Episodes
import com.example.lastproject.model.TvShow
import com.example.lastproject.view.adapter.EpisodeAdapter
import com.example.lastproject.view.adapter.ListMovieAdapter
import com.example.lastproject.view.adapter.SeasonAdapter
import com.example.lastproject.view.adapter.onClickSeasonInterface
import com.example.lastproject.viewmodel.TvShowViewModel
import com.squareup.picasso.Picasso


class TvShowDetailFragment : Fragment(), onClickSeasonInterface {

    lateinit var binding: FragmentTvShowDetailBinding

    lateinit var viewModel : TvShowViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowDetailBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]
        viewModel.selected.observe(viewLifecycleOwner,{
            setViews()
            setSeason(it.episodes.last().season)
        })


        viewModel.loading.observe(viewLifecycleOwner,{

            if (it == true){
                binding.progressBar.visibility = View.VISIBLE

            }
            else{
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.episodes.observe(viewLifecycleOwner,{
            setAdapter(it)
        })

        return binding.root
    }

    fun setViews(){


        var tvShow : TvShow = viewModel.selected.value!!
        binding.Date.text = tvShow.start_date
        binding.TextDescription.text = tvShow.description
        binding.TextTitle.text = tvShow.name

        Picasso.with(context)
            .load(tvShow.image_path)
            .placeholder(R.drawable.image_placeholder)
            .fit()
            .into(binding.imageView2)

        binding.rating.text = "Rating: " + tvShow.rating.toString()
        binding.TextState.text = tvShow.status
        if (tvShow.status.contains("Ended")){
            binding.TextState.setTextColor(Color.parseColor("#ED1000"))
        }
        else{
            binding.TextState.setTextColor(Color.parseColor("#618656"))

        }
      binding.ratingBar.rating = tvShow.rating.toFloat() / 2f
        binding.Country.text = " Country: " + tvShow.country
        binding.Network.text = " Network: " + tvShow.network
        binding.TextGenere.text = "Genere: " +tvShow.genres[1]
        binding.textRuntime.text = tvShow.runtime.toString() + " Minutes"
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    }

     fun setAdapter(items: List<Episodes>) {


        binding.RCVEpisodes.layoutManager =
            LinearLayoutManager(requireContext())
        binding.RCVEpisodes.adapter= EpisodeAdapter(this,items)
    }
    fun setSeason(season: Int){


        binding.RCVSeasons.adapter= SeasonAdapter(this,season,this)

    }

    override fun onSeasonClick(season: Int) {
        viewModel.loadTVShowDetail(viewModel.selected.value!!.id.toString(),season)
    }

}