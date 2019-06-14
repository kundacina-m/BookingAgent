package com.example.bookingagent.screens.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Message

class MessagesAdapter : BaseAdapter<Message>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		MessagesViewHolder(LayoutInflater.from(parent.context).inflate(
			R.layout.item_messages, parent, false
		))

}