package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.AccRoomDao
import com.example.bookingagent.data.db.dao.RoomDao
import com.example.bookingagent.data.db.entities.AccRoom
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.networking.room.RoomApi
import com.example.bookingagent.data.networking.room.models.DeleteRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeAddChangeRoomRequest
import com.example.bookingagent.data.networking.room.models.EnvelopeDeleteRoomRequest
import com.example.bookingagent.utils.toRequest
import com.example.bookingagent.utils.toSealed
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoomRepository @Inject constructor(
	private val roomApi: RoomApi,
	private val roomDao: RoomDao,
	private val accRoomDao: AccRoomDao) {

	// region NETWORK

	fun addRoom(accId: Int, room: Room) =
		roomApi.addRoom(EnvelopeAddChangeRoomRequest(room.toRequest(accId))).toSealed()

	fun editRoom(room: Room) =
		roomApi.editRoom(EnvelopeAddChangeRoomRequest(room.toRequest())).toSealed()

	fun deleteRoom(id: Int) =
		roomApi.deleteRoom(EnvelopeDeleteRoomRequest(DeleteRoomRequest(id = id.toInt()))).toSealed()

	// endregion NETWORK

	// region DB

	fun addRoomToDB(room: Room) =
		Single.just(roomDao.addRoom(room)).toSealed()

	fun getAllRoomsByAccId(accId: Int) =
		accRoomDao.getRoomsByAccId(accId).toSealed()

	fun addAccRoomRow(accId: Int, roomId: Int) =
		Completable.create {
			accRoomDao.addAccRoomRow(AccRoom(accId, roomId))
			it.onComplete()
		}.subscribeOn(Schedulers.io()).subscribe()!!

	fun getRoom(id: Int) =
		roomDao.getRoomById(id).toSealed()

	fun deleteRoomFromDB(id: Int) =
		Single.just(roomDao.deleteRoom(id)).toSealed()

	fun updateRoomInDB(room: Room) =
		Single.just(roomDao.updateRoom(room)).toSealed()

	// endregion DB
}