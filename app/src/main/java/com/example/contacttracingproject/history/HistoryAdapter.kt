package com.example.contacttracingproject.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.databinding.RecyclerViewRowBinding

class HistoryAdapter: ListAdapter<HistorySchema, HistoryAdapter.HistoryViewHolder>(HISTORY_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class HistoryViewHolder(val binding: RecyclerViewRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private val locationName: TextView = binding.locationName
        private val address: TextView = binding.address
        private val date: TextView = binding.date
        private val time: TextView = binding.time

        fun bind(history: HistorySchema) {
            locationName.text = history.name
            address.text = "Address:" + history.location
            date.text = "Date: " + history.date
            time.text = "Time: " + history.time
        }
    }

    companion object {
        private val HISTORY_COMPARATOR = object : DiffUtil.ItemCallback<HistorySchema>() {
            override fun areItemsTheSame(oldItem: HistorySchema, newItem: HistorySchema): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: HistorySchema, newItem: HistorySchema): Boolean {
                return oldItem.location == newItem.location && oldItem.date == newItem.date
            }
        }
    }
}