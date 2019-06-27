package com.example.bookingagent.screens.rooms

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.dialog_add_schedule.btCancel
import kotlinx.android.synthetic.main.dialog_add_schedule.btConfirm
import kotlinx.android.synthetic.main.dialog_add_schedule.etCheckIn
import kotlinx.android.synthetic.main.dialog_add_schedule.etCheckOut
import kotlinx.android.synthetic.main.dialog_add_schedule.etPrice
import java.util.Calendar
import java.util.GregorianCalendar

class AddScheduleDialog private constructor(private val builder: Builder) : Dialog(builder.context) {

	lateinit var checkInDay: GregorianCalendar
	lateinit var checkOutDay: GregorianCalendar

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_add_schedule)

		val cldr = Calendar.getInstance()
		val day = cldr.get(Calendar.DAY_OF_MONTH)
		val month = cldr.get(Calendar.MONTH)
		val year = cldr.get(Calendar.YEAR)

		etCheckIn.setOnFocusChangeListener { _, focused ->

			if (focused) {

				// date picker dialog
				val picker = DatePickerDialog(builder.context,
					DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
						etCheckIn.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
						checkInDay = GregorianCalendar(year, monthOfYear, dayOfMonth)
					}, year, month, day)
				picker.show()
			}
		}

		etCheckOut.setOnFocusChangeListener { _, focused ->
			if (focused) {

				// date picker dialog
				val picker = DatePickerDialog(builder.context,
					DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
						etCheckOut.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
						checkOutDay = GregorianCalendar(year, monthOfYear, dayOfMonth)
					}, year, month, day)

				picker.show()
			}
		}


		btConfirm.setOnClickListener {
			builder.confirmedTag.invoke(checkInDay, checkOutDay, etPrice.text.toString
			().toFloat())
			this.dismiss()
		}


		btCancel.setOnClickListener {
			this.dismiss()
		}

	}

	companion object {

		fun build(context: Context, block: Builder.() -> Unit): AddScheduleDialog {
			return Builder(context).apply(block).build()

		}
	}

	class Builder(var context: Context) {

		lateinit var confirmedTag: ((GregorianCalendar, GregorianCalendar, Float) -> Unit?)

		fun build() = AddScheduleDialog(this)

	}
}