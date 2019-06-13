package com.example.bookingagent.screens.messages

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.Message
import kotlinx.android.synthetic.main.item_messages.view.tvTitle
import kotlinx.android.synthetic.main.item_messages.view.tvUser

class MessagesViewHolder(itemView: View) : BaseViewHolder<Message>(itemView) {
	override fun bind(dataItem: Message) {
		itemView.apply {
			tvUser.text = dataItem.user
			tvTitle.text = dataItem.title
		}
	}
}