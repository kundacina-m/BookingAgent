package com.example.bookingagent.screens.accommodations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.model.Service

class ServicesAdapter : BaseAdapter<Service>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ServicesViewHolder(LayoutInflater.from(parent.context).inflate(
			R.layout.item_service, parent, false))

}