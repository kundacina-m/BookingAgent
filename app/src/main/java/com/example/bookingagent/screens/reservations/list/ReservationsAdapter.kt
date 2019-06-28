package com.example.bookingagent.screens.reservations.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.ReservationEntity
import kotlinx.android.synthetic.main.item_reservation.view.btUsed

class ReservationsAdapter : BaseAdapter<ReservationEntity>() {

	lateinit var onUsedClicked: (Int) -> Unit

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ReservationsViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.item_reservation, parent, false
			)
		)

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		super.onBindViewHolder(holder, position)
		holder.itemView.btUsed.setOnClickListener {
			onUsedClicked.invoke(getItemOnPosition(holder.adapterPosition).id)
		}
	}
}