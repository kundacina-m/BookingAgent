package com.example.bookingagent.screens.messages

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.MessageEntity
import kotlinx.android.synthetic.main.item_messages.view.tvTitle
import kotlinx.android.synthetic.main.item_messages.view.tvUser

class MessagesViewHolder(itemView: View) : BaseViewHolder<MessageEntity>(itemView) {
	override fun bind(dataItem: MessageEntity) {
		itemView.apply {
			tvUser.text = dataItem.firstname
			tvTitle.text = dataItem.lastname
		}
	}
}