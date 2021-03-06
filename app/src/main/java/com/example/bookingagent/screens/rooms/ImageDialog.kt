package com.example.bookingagent.screens.rooms

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Base64
import com.bumptech.glide.Glide
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.dialog_image.imageDialog

class ImageDialog
private constructor(private val builder: Builder) : Dialog(builder.context) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_image)

		invokeImageDialog()
	}

	private fun invokeImageDialog() =
		Glide.with(context)
			.asBitmap()
			.load(Base64.decode(builder.base64, Base64.DEFAULT))
			.into(imageDialog)

	companion object {

		fun build(context: Context, block: Builder.() -> Unit): ImageDialog {
			return Builder(context).apply(block).build()

		}
	}

	class Builder(var context: Context) {
		var base64: String? = null

		fun build() = ImageDialog(this)
	}
}