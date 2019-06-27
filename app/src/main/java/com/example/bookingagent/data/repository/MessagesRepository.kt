package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.MessageDao
import com.example.bookingagent.data.db.dao.ResMessDao
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.data.db.entities.ResMessEntity
import com.example.bookingagent.utils.toSealed
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MessagesRepository @Inject constructor(val messageDao: MessageDao, val resMessDao: ResMessDao) {

	fun addMessage(resId: Int, message: MessageEntity) {
		Single.just(messageDao.addMessage(message))
			.subscribeOn(Schedulers.io())
			.subscribeBy {
				if (it > 0)
					resMessDao.addResMess(ResMessEntity(resId, message.id))
			}
	}

	fun getAllMessagedFromDB() =
		resMessDao.getReservationsThatHaveMessages().toSealed()

	fun getMessageThread(resId: Int) =
		resMessDao.getMessageThreadByResId(resId).toSealed()

	fun sendMessage(resId: Int, message: String) {}
}