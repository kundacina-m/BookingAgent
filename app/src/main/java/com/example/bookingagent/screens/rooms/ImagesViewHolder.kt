package com.example.bookingagent.screens.rooms

import android.util.Base64
import android.view.View
import base.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_image.view.ivImage

class ImagesViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {
	override fun bind(dataItem: String) {

		Glide.with(itemView.context)
			.asBitmap()
			.load(Base64.decode(dataItem, Base64.DEFAULT))
			.apply(imgConfig())
			.into(itemView.ivImage)

		onImgClickListener(dataItem)
	}

	private fun onImgClickListener(dataItem: String) =
		itemView.ivImage.setOnClickListener {
			ImageDialog.build(itemView.context) {
				base64 = dataItem
			}.show()
		}

	private fun imgConfig() =
		RequestOptions()
			.centerCrop()
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.priority(Priority.HIGH)
}