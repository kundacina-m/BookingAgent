package com.example.bookingagent.screens.rooms.details

import android.view.View
import base.BaseViewHolder
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {

    override fun bind(dataItem: String) {
        itemView.tvComment.text = dataItem.dropLast(2)
    }
}