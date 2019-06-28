package com.example.bookingagent.screens.rooms.details

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bookingagent.App
import com.example.bookingagent.utils.WrappedResponse.OnSuccess

class DeleteRoomWorker(context: Context, workerParameters: WorkerParameters)
	: Worker(context, workerParameters) {

	val roomRepository by lazy { App.getInstance().roomRepository }

	override fun doWork(): Result {

		var done: Result = Result.failure()
		val roomId = inputData.getInt("roomId", 0)
		val it = roomRepository.deleteRoom(roomId)
			.blockingGet()

		if (it is OnSuccess)
			done = Result.success(Data.EMPTY)


		return done
	}
}