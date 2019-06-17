package com.example.bookingagent.screens.rooms

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.ScheduleUnit
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.item_schedule.view.tvActionRemove
import kotlinx.android.synthetic.main.item_schedule.view.tvCheckIn
import kotlinx.android.synthetic.main.item_schedule.view.tvCheckOut
import kotlinx.android.synthetic.main.item_schedule.view.tvPrice

class ScheduleViewHolder(itemView: View) : BaseViewHolder<ScheduleUnit>(itemView) {

	lateinit var itemRemove: (ScheduleUnit) -> Unit

	override fun bind(dataItem: ScheduleUnit) {
		populateView(dataItem)
		clickListeners(dataItem)
	}

	private fun populateView(dataItem: ScheduleUnit) =
		itemView.run {
			tvPrice.text = dataItem.price.toString()
			tvCheckIn.text = dataItem.checkIn.time.asString()
			tvCheckOut.text = dataItem.checkOut.time.asString()
		}

	private fun clickListeners(dataItem: ScheduleUnit) =
		itemView.tvActionRemove.setOnClickListener {
			itemRemove.invoke(dataItem)
		}
}