package com.example.bookingagent.screens.rooms.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.RoomEntity

class RoomsAdapter : BaseAdapter<RoomEntity>() {

	lateinit var onItemClickListener: (RoomEntity) -> Unit

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		RoomsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false))

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		super.onBindViewHolder(holder, position)
		holder.itemView.setOnClickListener {
			onItemClickListener.invoke(getItemOnPosition(holder.adapterPosition))
		}
	}
}