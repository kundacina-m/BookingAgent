package com.example.bookingagent.screens.rooms.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import base.BaseAdapter
import com.example.bookingagent.R
import com.example.bookingagent.data.model.ScheduleUnit

class ScheduleDetailsAdapter: BaseAdapter<ScheduleUnit>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ScheduleDetailsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_schedule_in_details, parent, false))
}