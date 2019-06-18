package com.example.bookingagent.screens.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R

class ImagesAdapter : BaseAdapter<String>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ImagesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
}