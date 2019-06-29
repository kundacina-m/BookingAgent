package com.example.bookingagent.screens.rooms

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.dialog_add_not_available.*
import java.util.*

class AddNotAvailableDialog private constructor(private val builder: Builder) : Dialog(builder.context) {

    private lateinit var checkInDay: GregorianCalendar
    private lateinit var checkOutDay: GregorianCalendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_not_available)

        val cldr = Calendar.getInstance()
        val day = cldr.get(Calendar.DAY_OF_MONTH)
        val month = cldr.get(Calendar.MONTH)
        val year = cldr.get(Calendar.YEAR)

        onCheckinTouched(year, month, day)
        onCheckoutTouched(year, month, day)
        setOnButtonClickListeners()

    }


    private fun onCheckoutTouched(year: Int, month: Int, day: Int) =
        etCheckOut.setOnFocusChangeListener { _, focused ->
            if (focused) {

                // date picker dialog
                val picker = DatePickerDialog(builder.context,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        etCheckOut.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                        checkOutDay = GregorianCalendar(year, monthOfYear, dayOfMonth)
                    }, year, month, day
                )

                picker.show()
            }
        }

    private fun onCheckinTouched(year: Int, month: Int, day: Int) =
        etCheckIn.setOnFocusChangeListener { _, focused ->

            if (focused) {

                // date picker dialog
                val picker = DatePickerDialog(builder.context,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        etCheckIn.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                        checkInDay = GregorianCalendar(year, monthOfYear, dayOfMonth)
                    }, year, month, day
                )
                picker.show()
            }
        }

    private fun setOnButtonClickListeners() {
        btConfirm.setOnClickListener {
            builder.confirmedTag.invoke(checkInDay, checkOutDay)
            this.dismiss()
        }

        btCancel.setOnClickListener {
            this.dismiss()
        }
    }

    companion object {

        fun build(context: Context, block: Builder.() -> Unit): AddNotAvailableDialog {
            return Builder(context).apply(block).build()

        }
    }

    class Builder(var context: Context) {

        lateinit var confirmedTag: ((GregorianCalendar, GregorianCalendar) -> Unit?)

        fun build() = AddNotAvailableDialog(this)

    }
}