package com.example.bookingagent.screens.accommodations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Accommodation

class AccommodationsAdapter : BaseAdapter<Accommodation>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		AccommodationsViewHolder(LayoutInflater.from(parent.context).inflate(
			R.layout.item_accommodation, parent, false
		))
}