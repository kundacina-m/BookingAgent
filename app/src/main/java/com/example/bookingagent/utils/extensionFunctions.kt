package com.example.bookingagent.utils

import android.widget.EditText

fun EditText.checkPasswordMatch(repeatedPassword: EditText, minLength: Int = 8): String? {
	return this.text.toString().takeIf {
		it == repeatedPassword.text.toString() && it.length >= minLength
	}
}