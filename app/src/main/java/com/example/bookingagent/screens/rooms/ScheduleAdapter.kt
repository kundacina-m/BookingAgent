package com.example.bookingagent.screens.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import base.BaseAdapter
import com.example.bookingagent.R.layout
import com.example.bookingagent.data.model.ScheduleUnit

class ScheduleAdapter : BaseAdapter<ScheduleUnit>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ScheduleViewHolder(LayoutInflater.from(parent.context).inflate(
			layout.item_schedule, parent, false)).apply {
			itemRemove = this@ScheduleAdapter::removeItem
		}

	private fun removeItem(item: ScheduleUnit) {
		val old = getData().toMutableList()
		old.remove(item)
		setData(old)
	}
}