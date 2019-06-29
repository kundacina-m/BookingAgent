package com.example.bookingagent.screens.messages.thread

import android.graphics.Color
import android.view.View
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.MessageEntity
import kotlinx.android.synthetic.main.item_message_thread.view.tvContent

class MessageThreadViewHolder(itemView: View) : BaseViewHolder<MessageEntity>(itemView) {
	override fun bind(dataItem: MessageEntity) {

		val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

		if (dataItem.sender == "user") {
			itemView.tvContent.setBackgroundColor(Color.parseColor("#e7d5d5"))
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
			params.marginEnd = 200
		} else {
			itemView.tvContent.setBackgroundColor(Color.parseColor("#67cfee"))
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
			params.marginStart = 200

		}
		itemView.tvContent.layoutParams = params
		itemView.tvContent.text = dataItem.content
	}
}