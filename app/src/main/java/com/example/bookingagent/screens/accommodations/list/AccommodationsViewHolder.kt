package com.example.bookingagent.screens.accommodations.list

import android.view.View
import base.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Accommodation
import kotlinx.android.synthetic.main.item_accommodation.view.ivImage
import kotlinx.android.synthetic.main.item_accommodation.view.tvDesc
import kotlinx.android.synthetic.main.item_accommodation.view.tvName

class AccommodationsViewHolder(itemView: View) : BaseViewHolder<Accommodation>(itemView) {

	override fun bind(dataItem: Accommodation) {
		itemView.apply {
			tvName.text = dataItem.name
			tvDesc.text = dataItem.description
		}

		Glide.with(itemView.context)
			.load(R.drawable.ic_image_placeholder_96dp)
			.apply(getGlideOptions())
			.into(itemView.ivImage)
	}

	private fun getGlideOptions(): RequestOptions =
		RequestOptions()
			.centerCrop()
			.placeholder(R.drawable.ic_image_placeholder_96dp)
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.priority(Priority.HIGH)
}