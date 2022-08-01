package com.example.contacttracingproject.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.BaseApplication
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.FragmentHistoryBinding
import com.example.contacttracingproject.profile.Profile
import com.journeyapps.barcodescanner.ScanContract
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class History : Fragment() {
    private var adapter = HistoryAdapter()

    lateinit var binding: FragmentHistoryBinding

    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((activity?.application as BaseApplication).historyRepository)
    }

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

//    private val zXingQRCodeScanLauncher = registerForActivityResult(ScanContract()) {
//            result ->
//        // Get callback results
//        if (result.contents == null) {
//            Toast.makeText(
//                getActivity(),
//                " Cancel ",
//                Toast.LENGTH_LONG
//            ).show()
//            Log.i("Scan", "Cancelled scan")
//        } else {
//            Log.i("scan_result", result.contents)
//
//            val address = result.contents
//            val current_date = LocalDate.now().toString()
//            val current_time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")).toString()
//            val history = HistorySchema(null, fullname, name, address, current_date, current_time)
//
//            historyViewModel.insert(history)
//            binding.locationView.getAdapter()?.notifyDataSetChanged()
//        }
//    }

    companion object {
        fun newInstance() = History()
    }
}