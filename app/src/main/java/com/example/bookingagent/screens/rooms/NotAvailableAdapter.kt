package com.example.bookingagent.screens.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.model.OccupiedTime

class NotAvailableAdapter : BaseAdapter<OccupiedTime>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        NotAvailableViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_not_available, parent, false))

}