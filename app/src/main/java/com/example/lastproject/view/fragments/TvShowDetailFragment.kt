package com.example.lastproject.view.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        viewModel.selected.observe(viewLifecycleOwner,{
            setViews()

            Toast.makeText(context,it.runtime.toString(),Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(viewLifecycleOwner,{

            if (it == true){
                binding.progressBar.visibility = View.VISIBLE

            }
            else{
                binding.progressBar.visibility = View.GONE
            }
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
            .centerCrop()
            .fit()
            .into(binding.imageView2)
        binding.rating.text = "Rating: " + tvShow.rating.toString()
        binding.TextState.text = tvShow.status
             binding.TextGenere!!.text = "Genere: " +tvShow.genres[1]
            binding.textRuntime!!.text = tvShow.runtime.toString() + " Minutes"
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;


    }

}