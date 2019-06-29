package com.example.bookingagent.screens.messages

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.model.MessageThread
import kotlinx.android.synthetic.main.item_messages.view.*

class MessagesViewHolder(itemView: View) : BaseViewHolder<MessageThread>(itemView) {
    override fun bind(dataItem: MessageThread) {
        itemView.tvName.text = (dataItem.firstname + " " + dataItem.lastname)
        itemView.tvResId.text = dataItem.resId.toString()
    }
}