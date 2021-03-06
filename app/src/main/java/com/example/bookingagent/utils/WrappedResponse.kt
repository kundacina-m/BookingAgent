package com.example.bookingagent.utils

import com.example.bookingagent.utils.RequestError.HttpError
import com.example.bookingagent.utils.RequestError.NoInternetError
import com.example.bookingagent.utils.RequestError.UnknownError
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

sealed class WrappedResponse<out T> {

	data class OnSuccess<out T>(val item: T) : WrappedResponse<T>()
	data class OnError<out T>(val error: RequestError) : WrappedResponse<T>()

}

sealed class RequestError {
	data class UnknownError(val t: Throwable) : RequestError()
	data class NoInternetError(val t: Throwable) : RequestError()
	data class HttpError(val code: Int, val message: String) : RequestError()
}

object RequestErrorParser {
	fun parse(exception: Throwable): RequestError =

		when (exception) {
			is HttpException -> HttpError(exception.code(), exception.message())
			is UnknownHostException -> NoInternetError(exception)
			is ConnectException -> NoInternetError(exception)
			else -> UnknownError(exception)
		}

}