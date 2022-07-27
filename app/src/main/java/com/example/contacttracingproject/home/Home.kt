package com.example.contacttracingproject.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.FragmentHomeBinding

//Home Fragment

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root

        binding.viewModel = homeViewModel

        binding.lifecycleOwner = this

        suspend fun setUser() {
            val user = homeViewModel.getUser()
            Log.i("icNo7", homeViewModel.fullName.value.toString())
            Log.i("icNo8", homeViewModel.nric.value.toString())
        }
    }

    companion object {
        fun newInstance() = Home()
    }
}