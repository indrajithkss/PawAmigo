package com.app.pawamigo.ui.playdate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pawamigo.R

class PlaydateAdapter(private val list: List<Playdate>) :
    RecyclerView.Adapter<PlaydateAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPetName: TextView = itemView.findViewById(R.id.tvPetName)
        val tvOwnerName: TextView = itemView.findViewById(R.id.tvOwnerName)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvNotes: TextView = itemView.findViewById(R.id.tvNotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playdate, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvPetName.text = item.petName
        holder.tvOwnerName.text = "Owner: ${item.ownerName}"
        holder.tvDate.text = "Date: ${item.date}"
        holder.tvTime.text = "Time: ${item.time}"
        holder.tvNotes.text = "Notes: ${item.notes}"
    }

    override fun getItemCount(): Int = list.size
}
