package com.example.bookingagent.screens.accommodations.details

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bookingagent.App
import com.example.bookingagent.utils.WrappedResponse.OnSuccess

class DeleteAccommodationWorker(context: Context, workerParameters: WorkerParameters)
	: Worker(context, workerParameters) {

	val accommodationRepository by lazy { App.getInstance().accommodationRepository }

	override fun doWork(): Result {

		var done: Result = Result.failure()

		val accId = inputData.getInt("accId", 0)

		val it = accommodationRepository.deleteAccommodation(accId)
			.blockingGet()

		if (it is OnSuccess)
			done = Result.success(Data.EMPTY)

		return done
	}
}