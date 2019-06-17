package com.example.bookingagent.screens.reservations.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Reservation

class ReservationsAdapter : BaseAdapter<Reservation>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ReservationsViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.item_reservation, parent, false
			)
		)
}