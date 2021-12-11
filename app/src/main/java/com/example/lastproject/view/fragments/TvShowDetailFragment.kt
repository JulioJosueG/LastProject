package com.example.lastproject.view.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lastproject.R
import com.example.lastproject.databinding.FragmentTvShowDetailBinding
import com.example.lastproject.model.TvShow
import com.example.lastproject.viewmodel.TvShowViewModel
import com.squareup.picasso.Picasso


class TvShowDetailFragment : Fragment() {

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
        var tvShow : TvShow = viewModel.selected.value!!
        viewModel.loadTVShowDetail(tvShow.id.toString())

        binding.Date.text = tvShow.start_date
        binding.TextDescription.text = tvShow.description
        binding.TextTitle.text = tvShow.name

        Picasso.with(context)
            .load(tvShow.image_path)
            .placeholder(R.drawable.image_placeholder)
            .centerCrop()
            .fit()
            .into(binding.imageView2)
        binding.rating.text = tvShow.rating.toString()
        binding.TextState.text = tvShow.status
        /*      binding.TextGenere!!.text = tvShow.genres[1]
            binding.textRuntime!!.text = tvShow.runtime.toString()*/
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

        return binding.root
    }

}