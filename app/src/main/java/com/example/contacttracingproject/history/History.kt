package com.example.contacttracingproject.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.R

class History : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        location_view
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

//    Apply the RecyclerView to the History XML File
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val location_view: RecyclerView = view.findViewById(R.id.location_view)
        location_view.setNestedScrollingEnabled(false)
        location_view.apply {
            layoutManager = LinearLayoutManager(activity)

            adapter = HistoryAdapter()
        }
    }

    companion object {
        fun newInstance() = History()
    }
}