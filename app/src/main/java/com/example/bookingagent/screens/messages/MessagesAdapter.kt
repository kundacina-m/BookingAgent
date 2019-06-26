package com.example.bookingagent.screens.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.MessageEntity

class MessagesAdapter : BaseAdapter<MessageEntity>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		MessagesViewHolder(LayoutInflater.from(parent.context).inflate(
			R.layout.item_messages, parent, false
		))

}