package com.example.bookingagent.screens.accommodations.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Service

class ServicesAdapter : BaseAdapter<Service>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ServicesViewHolder(LayoutInflater.from(parent.context).inflate(
			R.layout.item_service, parent, false))

}