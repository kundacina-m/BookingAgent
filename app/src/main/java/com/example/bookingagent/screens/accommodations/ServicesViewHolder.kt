package com.example.bookingagent.screens.accommodations

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.model.Service
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.item_service.view.tvDesc
import kotlinx.android.synthetic.main.item_service.view.tvName
import kotlinx.android.synthetic.main.item_service.view.tvPrice

class ServicesViewHolder(itemView: View) : BaseViewHolder<Service>(itemView) {
	override fun bind(dataItem: Service) {
		itemView.tvName.text = dataItem.name
		itemView.tvDesc.text = dataItem.description
		itemView.tvPrice.text = dataItem.price.asString()
	}
}