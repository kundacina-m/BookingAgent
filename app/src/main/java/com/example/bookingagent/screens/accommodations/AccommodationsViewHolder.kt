package com.example.bookingagent.screens.accommodations

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.Accommodation
import kotlinx.android.synthetic.main.item_accommodation.view.tvDesc
import kotlinx.android.synthetic.main.item_accommodation.view.tvName

class AccommodationsViewHolder(itemView: View) : BaseViewHolder<Accommodation>(itemView) {

	override fun bind(dataItem: Accommodation) {
		itemView.apply {
			tvName.text = dataItem.name
			tvDesc.text = dataItem.description
		}
	}
}