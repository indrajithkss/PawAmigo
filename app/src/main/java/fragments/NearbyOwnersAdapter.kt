package com.app.pawamigo.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pawamigo.R
import android.widget.Toast


class NearbyOwnersAdapter(
    private val ownersList: List<NearbyOwner>
) : RecyclerView.Adapter<NearbyOwnersAdapter.OwnerViewHolder>() {

    class OwnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPetCard: ImageView = itemView.findViewById(R.id.imgPetCard)
        val tvOwnerName: TextView = itemView.findViewById(R.id.tvOwnerName)
        val tvPetName: TextView = itemView.findViewById(R.id.tvPetName)
        val tvPetType: TextView = itemView.findViewById(R.id.tvPetType)
        val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
        val btnConnect: Button = itemView.findViewById(R.id.btnConnect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nearby_owner, parent, false)
        return OwnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        val owner = ownersList[position]

        holder.imgPetCard.setImageResource(owner.imageRes)
        holder.tvOwnerName.text = owner.ownerName
        holder.tvPetName.text = owner.petName
        holder.tvPetType.text = owner.petType
        holder.tvDistance.text = owner.distance

        holder.btnConnect.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Connect request sent to ${owner.ownerName}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun getItemCount(): Int = ownersList.size
}
