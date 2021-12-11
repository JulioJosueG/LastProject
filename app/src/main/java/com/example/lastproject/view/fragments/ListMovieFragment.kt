package com.example.lastproject.view.fragments

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
import com.example.lastproject.databinding.FragmentListMovieBinding
import com.example.lastproject.model.TvShow
import com.example.lastproject.view.adapter.ListMovieAdapter
import com.example.lastproject.view.adapter.onClickTvShowInterface
import com.example.lastproject.viewmodel.TvShowViewModel


class ListMovieFragment : Fragment() , onClickTvShowInterface {

    lateinit var binding : FragmentListMovieBinding

    lateinit var viewModel : TvShowViewModel

    private var q : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentListMovieBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]
        viewModel.tvShowList.observe(viewLifecycleOwner,{

            setAdapter(it)
        })

        viewModel.pagination.observe(viewLifecycleOwner,{
            binding.CurrentText.text = "Current Page: " + it.page.toString() + "/" + it.totalPages.toString()

        })

        viewModel.exception.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(viewLifecycleOwner,{

            if (it == true){
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.progressBar.visibility = View.GONE

            }


        })

        viewModel.loadTvShows();



        binding.btnNext.setOnClickListener{
            val nextPage : Int = viewModel.pagination.value!!.page+1;
            if (nextPage <= viewModel.pagination.value!!.totalPages){
                if (viewModel.TypeInt ==0)
                viewModel.loadTvShows(nextPage)
                if (viewModel.TypeInt ==1)
                    viewModel.search(q,nextPage)

            }
            else{
                Toast.makeText(requireContext(),"No More Pages", Toast.LENGTH_SHORT).show()
            }

        }

        binding.floatActionSearch.setOnClickListener{
            q=binding.SearchText.text.toString()
            viewModel.search(q)


        }
        return binding.root;
    }

    private fun setAdapter(items: List<TvShow>) {
        binding.TvRV.layoutManager =
            LinearLayoutManager(requireContext())
        binding.TvRV.adapter= ListMovieAdapter(this,items,this){
            viewModel.select(it)
        }


    }

    override fun onTvShowClick(tvShow: TvShow) {
        viewModel.select(tvShow)
        findNavController().navigate(R.id.action_listMovieFragment_to_tvShowDetailFragment)
    }


}