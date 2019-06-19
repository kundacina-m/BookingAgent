package com.example.bookingagent.screens.accommodations

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.dialog_add_schedule.etPrice
import kotlinx.android.synthetic.main.dialog_add_service.btCancel
import kotlinx.android.synthetic.main.dialog_add_service.btConfirm
import kotlinx.android.synthetic.main.dialog_add_service.etDescription
import kotlinx.android.synthetic.main.dialog_add_service.etName

class DialogAddService private constructor(private val builder: Builder) : Dialog(builder.context) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_add_service)

		btConfirm.setOnClickListener {
			builder.confirmedService.invoke(etName.text.toString(), etDescription.text.toString(), etPrice.text.toString
			().toFloat())
			this.dismiss()
		}


		btCancel.setOnClickListener {
			this.dismiss()
		}

	}

	companion object {

		fun build(context: Context, block: Builder.() -> Unit): DialogAddService {
			return Builder(context).apply(block).build()

		}
	}

	class Builder(var context: Context) {

		lateinit var confirmedService: ((String, String, Float) -> Unit?)

		fun build() = DialogAddService(this)

	}

}