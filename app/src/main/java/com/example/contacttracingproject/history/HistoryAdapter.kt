package com.example.contacttracingproject.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.R

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

//    HistoryAdapter for the RecyclerView in the XML file

    var addressData = arrayOf("Kelana Jaya", "Prangin Mall", "Paragon", "Gurney Plaza", "Batu Ferringhi", "Sungai Petani")
    var dateData = arrayOf("12/12/2001", "26/06/1996", "2/5/1968", "11/05/2003", "03/01/2003", "12/09/2012")
    var timeData = arrayOf("2.00pm", "10.00am", "12.00am", "6.00pm", "3.30pm", "9.00pm")

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var address: TextView
        var date: TextView
        var time: TextView

        init {
            address = itemView.findViewById(R.id.address)
            date = itemView.findViewById(R.id.date)
            time = itemView.findViewById(R.id.time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.address.text = "Address: " + addressData[position]
        holder.date.text = "Date: " + dateData[position]
        holder.time.text = "Time: " + timeData[position]
    }

    override fun getItemCount(): Int {
        return addressData.size
    }
}