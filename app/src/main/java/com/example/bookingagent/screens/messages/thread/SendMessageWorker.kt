package com.example.bookingagent.screens.messages.thread

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bookingagent.App
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.google.gson.Gson

class SendMessageWorker(context: Context, workerParameters: WorkerParameters)
	: Worker(context, workerParameters) {

	val messagesRepository by lazy { App.getInstance().messagesRepository }

	override fun doWork(): Result {

		var done: Result = Result.failure()

		val resId = inputData.getInt("resId", 0)
		val message = inputData.getString("message")
		val data = inputData.getString("data")

		val messageEntity = Gson().fromJson(data, MessageEntity::class.java)

		val it = messagesRepository.sendMessage(resId, message!!)
			.blockingGet()



		if (it is OnSuccess) {
			messagesRepository.addMessage(resId,
				MessageEntity(it.item.body.body.messageId, messageEntity.firstname, messageEntity.lastname,
					message, messageEntity.agentEmail, messageEntity.sender))
			done = Result.success(Data.EMPTY)
		}


		return done
	}
}