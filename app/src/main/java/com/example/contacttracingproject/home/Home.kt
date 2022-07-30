package com.example.contacttracingproject.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracingproject.BaseApplication
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.FragmentHomeBinding
import com.example.contacttracingproject.registration.SignUpViewModel

//Home Fragment

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fullname: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel: HomeViewModel by viewModels {
            HomeViewModelFactory((activity?.application as BaseApplication).repository)
        }
        binding.viewModel = homeViewModel

        binding.lifecycleOwner = this

        val intent = requireActivity().intent
        fullname = intent.getStringExtra("fullname").toString()

        homeViewModel.getUser(fullname)

    }



    companion object {
        fun newInstance() = Home()
    }
}