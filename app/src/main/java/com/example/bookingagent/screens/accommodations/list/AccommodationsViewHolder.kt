package com.example.bookingagent.screens.accommodations.list

import android.util.Base64
import android.view.View
import base.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.screens.rooms.ImageDialog
import kotlinx.android.synthetic.main.item_accommodation.view.ivImage
import kotlinx.android.synthetic.main.item_accommodation.view.tvDesc
import kotlinx.android.synthetic.main.item_accommodation.view.tvName

class AccommodationsViewHolder(itemView: View) : BaseViewHolder<AccommodationEntity>(itemView) {

	override fun bind(dataItem: AccommodationEntity) {
		itemView.apply {
			tvName.text = dataItem.name
			tvDesc.text = dataItem.description
		}

		val imageByteArray = Base64.decode(dataItem.pictures[0], Base64.DEFAULT)

		val options = RequestOptions()
			.centerCrop()
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.priority(Priority.HIGH)

		Glide.with(itemView.context)
			.asBitmap()
			.load(imageByteArray)
			.apply(options)
			.into(itemView.ivImage)

		itemView.ivImage.setOnClickListener {
			ImageDialog.build(itemView.context) {
				base64 = dataItem.pictures[0]
			}.show()
		}
	}

}