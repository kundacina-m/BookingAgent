package com.example.bookingagent.screens.messages.thread

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType.CONNECTED
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import base.BaseViewModel
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.utils.RequestError.NoInternetError
import com.example.bookingagent.utils.WrappedResponse
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MessageThreadViewModel @Inject constructor(private val messagesRepository: MessagesRepository,
	val context: Context) :
	BaseViewModel() {

	val addedMessage = MutableLiveData<MessageEntity>()
	val messages = MutableLiveData<WrappedResponse<List<MessageEntity>>>()
	val messageSentStatus = MutableLiveData<WrappedResponse<Int>>()

	fun fetchMessages(resId: Int) =
		disposables.add(messagesRepository.getMessageThread(resId)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				messages.postValue(it)
			})

	fun sendMessage(resId: Int, message: String) {
		disposables.add(messagesRepository.sendMessage(resId, message)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is WrappedResponse.OnSuccess -> {
						messageSentStatus.postValue(WrappedResponse.OnSuccess(it.item.body.body.messageId))
						addMessageToDB(resId, message, it.item.body.body.messageId)
					}
					is WrappedResponse.OnError -> {
						if (it.error is NoInternetError) {
							val data = (messages.value as WrappedResponse.OnSuccess).item[0]
							val messageEntity = MessageEntity(0, data.firstname, data.lastname, message, data
								.agentEmail, "agent")

							WorkManager.getInstance().enqueue(
								OneTimeWorkRequestBuilder<SendMessageWorker>()
									.setInputData(createInputDataForInput(resId, message, messageEntity))
									.setConstraints(
										Constraints.Builder()
											.setRequiredNetworkType(CONNECTED).build()
									).build()
							)
						}
						messageSentStatus.postValue(WrappedResponse.OnError(it.error))
					}
				}
			})
	}

	private fun addMessageToDB(resId: Int, message: String, messageId: Int) {
		val data = (messages.value as WrappedResponse.OnSuccess).item[0]
		val messageEntity = MessageEntity(messageId, data.firstname, data.lastname, message, data.agentEmail, "agent")

		addedMessage.postValue(messageEntity)

		disposables.add(Single.just(messagesRepository).subscribeOn(Schedulers.io())
			.subscribeBy {
				it.addMessage(resId, messageEntity)
			})

	}

	fun deleteMessage(id: Int) =
		disposables.add(messagesRepository.deleteMessage(id)
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				when (it) {
					is WrappedResponse.OnSuccess -> messagesRepository.deleteMessageFromDB(id)
					is WrappedResponse.OnError -> {
						if (it.error is NoInternetError) {
							WorkManager.getInstance().enqueue(
								OneTimeWorkRequestBuilder<DeleteMessageWorker>()
									.setInputData(createInputDataForMessage(id))
									.setConstraints(
										Constraints.Builder()
											.setRequiredNetworkType(CONNECTED).build()
									).build()
							)
						}
						messagesRepository.deleteMessageFromDB(id)
					}
				}
			})

	private fun createInputDataForMessage(id: Int): Data =
		Data.Builder().run {
			putInt("messId", id)
		}.build()

	private fun createInputDataForInput(resId: Int, message: String, messageEntity: MessageEntity) =
		Data.Builder().run {
			putInt("resId", resId)
			putString("message", message)
			putString("data", Gson().toJson(messageEntity))
		}.build()
}