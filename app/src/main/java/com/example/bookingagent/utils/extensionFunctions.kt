package com.example.bookingagent.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.DecimalFormat
import android.util.Base64
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun EditText.checkPasswordMatch(repeatedPassword: EditText, minLength: Int = 8): String? {
	return this.text.toString().takeIf {
		it == repeatedPassword.text.toString() && it.length >= minLength
	}
}

// Mapping "Flowable type" incoming data or error to my WrappedResponse
fun <T> Flowable<T>.toSealed(): Flowable<WrappedResponse<T>> {

	return this.map<WrappedResponse<T>> { OnSuccess(it) }
		.onErrorReturn {
			OnError(RequestErrorParser.parse(exception = it))
		}
}

// Mapping "Single type" incoming data or error to my WrappedResponse
fun <T> Single<T>.toSealed(): Single<WrappedResponse<T>> {

	return this.map<WrappedResponse<T>> { OnSuccess(it) }
		.onErrorReturn {
			OnError(RequestErrorParser.parse(exception = it))
		}
}

// Mapping "Observable type" incoming data or error to my WrappedResponse
fun <T> Observable<T>.toSealed(): Observable<WrappedResponse<T>> {

	return this.map<WrappedResponse<T>> { OnSuccess(it) }
		.onErrorReturn {
			OnError(RequestErrorParser.parse(exception = it))
		}
}

fun Bitmap.toBase64(): String =
	ByteArrayOutputStream().let {
		this.compress(Bitmap.CompressFormat.JPEG, 100, it)
		Base64.encodeToString(it.toByteArray(), Base64.DEFAULT)
	}

fun EditText.asString() =
	this.text.toString()

fun Date.asString(): String =
	SimpleDateFormat("dd. MMMM yyyy", Locale.getDefault()).format(this)

fun Fragment.showToast(message: String) =
	Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()

fun RecyclerView.isGone(visibility: Boolean) =
	if (visibility) this.visibility = View.VISIBLE else this.visibility = View.GONE

fun RecyclerView.isHidden(visibility: Boolean) =
	if (visibility) this.visibility = View.VISIBLE else this.visibility = View.INVISIBLE

fun TextView.isGone(visibility: Boolean) =
	if (visibility) this.visibility = View.VISIBLE else this.visibility = View.GONE

fun TextView.isHidden(visibility: Boolean) =
	if (visibility) this.visibility = View.VISIBLE else this.visibility = View.INVISIBLE

fun Float.asString() =
	String.format("%.2f",this) + "$"

fun String.asDate() =
	SimpleDateFormat("yyyy-MM-dd").parse(this)

fun String.compressBase64(): String {
	Base64.decode(this.toByteArray(), Base64.DEFAULT).also {
		BitmapFactory.decodeByteArray(it, 0, it.size, BitmapFactory.Options().apply { inPurgeable = true }).run {
			if (height <= 200 && width <= 200) return this@compressBase64
			Bitmap.createScaledBitmap(this, 200, 200, false)
			ByteArrayOutputStream().also { baos ->
				compress(Bitmap.CompressFormat.PNG, 100, baos)
				return Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP)
			}
		}
	}
}

//		("#,###.00")