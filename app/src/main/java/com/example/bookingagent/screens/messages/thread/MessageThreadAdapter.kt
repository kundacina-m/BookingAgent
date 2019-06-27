package com.example.bookingagent.screens.messages.thread

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.fragment_message_thread.view.btSendMessage
import kotlinx.android.synthetic.main.fragment_message_thread.view.etMessage

class MessageThreadAdapter : BaseAdapter<MessageEntity>() {

    lateinit var onLongClickListener: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        MessageThreadViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_message_thread, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnLongClickListener {
            onLongClickListener.invoke(getItemOnPosition(holder.adapterPosition).id)
            true
        }
    }

}