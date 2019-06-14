package com.example.bookingagent.screens.accommodations.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R.layout
import com.example.bookingagent.data.db.entities.Accommodation

class AccommodationsAdapter : BaseAdapter<Accommodation>() {

	lateinit var onItemClickListener: (Accommodation) -> Unit

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		AccommodationsViewHolder(
			LayoutInflater.from(parent.context).inflate(
				layout.item_accommodation, parent, false
			))

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		super.onBindViewHolder(holder, position)

		holder.itemView.setOnClickListener {
			onItemClickListener.invoke(getItemOnPosition(holder.adapterPosition))
		}
	}
}