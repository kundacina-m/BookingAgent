package com.example.bookingagent.screens.rooms

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.model.OccupiedTime
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.item_not_available.view.*

class NotAvailableViewHolder(itemView: View): BaseViewHolder<OccupiedTime>(itemView) {
    override fun bind(dataItem: OccupiedTime) {
        itemView.tvFrom.text = dataItem.startDate.time.asString()
        itemView.tvTo.text = dataItem.endDate.time.asString()
    }
}