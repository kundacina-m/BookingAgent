package com.example.bookingagent.screens.reservations.list

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bookingagent.App
import com.example.bookingagent.utils.WrappedResponse.OnSuccess

class UseReservationWorker(context: Context, workerParameters: WorkerParameters)
	: Worker(context, workerParameters) {

	val reservationRepository by lazy { App.getInstance().reservationRepository }

	override fun doWork(): Result {

		var done: Result = Result.failure()
		val resId = inputData.getInt("resId", 0)
		val it = reservationRepository.reservationUsed(resId)
			.blockingGet()

		if (it is OnSuccess)
			done = Result.success(Data.EMPTY)

		return done
	}
}