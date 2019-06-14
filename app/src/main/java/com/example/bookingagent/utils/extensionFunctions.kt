package com.example.bookingagent.utils

import android.widget.EditText
import com.example.bookingagent.utils.RequestError.UnknownError
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

fun EditText.checkPasswordMatch(repeatedPassword: EditText, minLength: Int = 8): String? {
	return this.text.toString().takeIf {
		it == repeatedPassword.text.toString() && it.length >= minLength
	}
}

// Mapping "Flowable type" incoming data or error to my WrappedResponse
fun <T> Flowable<T>.toSealed(): Flowable<WrappedResponse<T>> {

	return this.map<WrappedResponse<T>> { OnSuccess(it) }
		.onErrorReturn {
			OnError(UnknownError(it))
		}
}

// Mapping "Single type" incoming data or error to my WrappedResponse
fun <T> Single<T>.toSealed(): Single<WrappedResponse<T>> {

	return this.map<WrappedResponse<T>> { OnSuccess(it) }
		.onErrorReturn {
			OnError(UnknownError(it))
		}
}

// Mapping "Observable type" incoming data or error to my WrappedResponse
fun <T> Observable<T>.toSealed(): Observable<WrappedResponse<T>> {

	return this.map<WrappedResponse<T>> { OnSuccess(it) }
		.onErrorReturn {
			OnError(UnknownError(it))
		}
}