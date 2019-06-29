package com.example.bookingagent.screens.rooms.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import base.BaseAdapter
import com.example.bookingagent.R

class CommentsAdapter : BaseAdapter<String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))

}