package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.MessageDao
import com.example.bookingagent.data.db.dao.ResMessDao
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.data.db.entities.ResMessEntity
import com.example.bookingagent.data.networking.message.MessageApi
import com.example.bookingagent.data.networking.message.models.AddMessageRequest
import com.example.bookingagent.data.networking.message.models.DeleteMessageRequest
import com.example.bookingagent.data.networking.message.models.EnvelopeAddMessageRequest
import com.example.bookingagent.data.networking.message.models.EnvelopeDeleteMessageRequest
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MessagesRepository @Inject constructor(val messageDao: MessageDao, val resMessDao: ResMessDao,
	val messageApi: MessageApi) {

	fun addMessage(resId: Int, message: MessageEntity) =
		Single.just(messageDao.addMessage(message))
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				if (it > 0)
					resMessDao.addResMess(ResMessEntity(resId, message.id))
			}

	fun getAllMessagedFromDB() =
		resMessDao.getReservationsThatHaveMessages().toSealed()

	fun getMessageThread(resId: Int) =
		resMessDao.getMessageThreadByResId(resId).toSealed()

	fun deleteMessageFromDB(id: Int) =
		messageDao.deleteMessage(id)

	// region NETWORK

	fun sendMessage(resId: Int, message: String) =
		messageApi.addMessage(EnvelopeAddMessageRequest(AddMessageRequest(resId, message))).toSealed()

	fun deleteMessage(messageId: Int) =
		messageApi.deleteMessage(EnvelopeDeleteMessageRequest(DeleteMessageRequest(messageId))).toSealed()

	fun deleteAllMessages() {
		messageDao.nukeMessageTable()
		resMessDao.nukeResMessTable()
	}

	// endregion NETWORK

}