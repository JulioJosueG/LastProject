package com.example.lastproject.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.lastproject.R
import com.example.lastproject.databinding.FragmentStartUpBinding


class StartUpFragment : Fragment() {
    lateinit var binding: FragmentStartUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartUpBinding.inflate(inflater,container,false)

        binding.Boton.setOnClickListener {
            it.findNavController().navigate(R.id.action_startUpFragment_to_listMovieFragment)

        }

        return binding.root
    }


}