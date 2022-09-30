package com.example.contacttracingproject.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//Home Fragment
@AndroidEntryPoint
class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var nric: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel: HomeViewModel by viewModels()
        binding.viewModel = homeViewModel

        binding.lifecycleOwner = this

        val intent = requireActivity().intent
        nric = intent.getStringExtra("nric").toString()
        Log.i("homeFragment", nric)

        lifecycleScope.launch {
            homeViewModel.getUser(nric)
        }
    }

    companion object {
        fun newInstance() = Home()
    }
}