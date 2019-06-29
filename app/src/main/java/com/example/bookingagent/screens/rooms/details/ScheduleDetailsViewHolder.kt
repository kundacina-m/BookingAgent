package com.example.bookingagent.screens.rooms.details

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.item_schedule_in_details.view.*

class ScheduleDetailsViewHolder(itemView: View) : BaseViewHolder<ScheduleUnit>(itemView) {

    override fun bind(dataItem: ScheduleUnit) {
        populateView(dataItem)
    }

    private fun populateView(dataItem: ScheduleUnit) =
        itemView.run {
            tvPrice.text = dataItem.price.asString()
            tvCheckIn.text = dataItem.checkIn.time.asString()
            tvCheckOut.text = dataItem.checkOut.time.asString()
        }


}