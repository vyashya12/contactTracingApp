package com.example.contacttracingproject.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacttracingproject.databinding.FragmentHistoryBinding

class History : Fragment() {
    private var adapter = HistoryAdapter()

    lateinit var binding: FragmentHistoryBinding

    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        historyViewModel.allHistories.observe(owner = viewLifecycleOwner) { histories ->
            histories.let { adapter.submitList(it) }
        }
    }

    private fun setupAdapter() {
        //adapter = HistoryListAdapter()
        binding.locationView.adapter = adapter
        binding.locationView.layoutManager = LinearLayoutManager(activity)
    }

    companion object {
        fun newInstance() = History()
    }
}