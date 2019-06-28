package com.example.bookingagent.screens.messages.thread

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bookingagent.App
import com.example.bookingagent.utils.WrappedResponse.OnSuccess

class DeleteMessageWorker(context: Context, workerParameters: WorkerParameters)
	: Worker(context, workerParameters) {

	val messagesRepository by lazy { App.getInstance().messagesRepository }

	override fun doWork(): Result {

		var done: Result = Result.failure()

		val messageId = inputData.getInt("messId", 0)

		val it = messagesRepository.deleteMessage(messageId)
			.blockingGet()

		if (it is OnSuccess)
			done = Result.success(Data.EMPTY)

		return done
	}
}