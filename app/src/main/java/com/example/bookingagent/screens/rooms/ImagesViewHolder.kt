package com.example.bookingagent.screens.rooms

import android.util.Base64
import android.view.View
import base.BaseViewHolder
import com.bumptech.glide.Glide
import com.example.bookingagent.utils.BROKEN_IMAGE
import kotlinx.android.synthetic.main.item_image.view.ivImage

class ImagesViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {
	override fun bind(dataItem: String) {
//		val imageByteArray = Base64.decode(dataItem.src, Base64.DEFAULT)
		val imageByteArray = Base64.decode(BROKEN_IMAGE, Base64.DEFAULT)
		Glide.with(itemView.context)
			.asBitmap()
			.load(imageByteArray)
			.into(itemView.ivImage)

		itemView.ivImage.setOnClickListener {
			DialogImage.build(itemView.context) {
				base64 = BROKEN_IMAGE
			}.show()
		}
	}
}