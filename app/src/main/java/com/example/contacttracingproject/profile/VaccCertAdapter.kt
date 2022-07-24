package com.example.contacttracingproject.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacttracingproject.R
import com.example.contacttracingproject.VaccDoses

//Vaccine Certificate RecyclerView Adapter
class VaccCertAdapter : RecyclerView.Adapter<VaccCertAdapter.VaccViewHolder>() {
    val doses : MutableList<VaccDoses> = mutableListOf(
        VaccDoses(
            id = 1,
            date = "1/2/3456",
            time = "12:34PM",
            brand = "Pfizer",
            vacc_centre = "Olympus Mons, Mars"
        ),
        VaccDoses(
            id = 2,
            date = "1/2/3456",
            time = "12:34PM",
            brand = "Pfizer",
            vacc_centre = "Olympus Mons, Mars"
        ),
        VaccDoses(
            id = 3,
            date = "1/2/3456",
            time = "12:34PM",
            brand = "Pfizer",
            vacc_centre = "Olympus Mons, Mars"
        ),
        VaccDoses(
            id = 4,
            date = "1/2/3456",
            time = "12:34PM",
            brand = "Pfizer",
            vacc_centre = "Olympus Mons, Mars"
        ),
        VaccDoses(
            id = 5,
            date = "1/2/3456",
            time = "12:34PM",
            brand = "Pfizer",
            vacc_centre = "Olympus Mons, Mars"
        )
    )

    class VaccViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dose_id = itemView.findViewById<TextView>(R.id.dose_id)
        val dose_date = itemView.findViewById<TextView>(R.id.dose_date)
        val dose_time = itemView.findViewById<TextView>(R.id.dose_time)
        val dose_brand = itemView.findViewById<TextView>(R.id.dose_brand)
        val dose_vacc_centre = itemView.findViewById<TextView>(R.id.dose_vacc_centre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): VaccViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.vacc_dose_item, viewGroup, false)
        return VaccViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: VaccViewHolder, i: Int) {
        viewHolder.dose_id.text = "Dose " + doses[i].id.toString()
        viewHolder.dose_date.text = "Date : " + doses[i].date
        viewHolder.dose_time.text = "Time : " + doses[i].time
        viewHolder.dose_brand.text = "Brand : " + doses[i].brand
        viewHolder.dose_vacc_centre.text = "Vacc. Centre : " + doses[i].vacc_centre
    }

    override fun getItemCount(): Int {
        return doses.size
    }
}