package com.example.bookingagent.data.repository

import com.example.bookingagent.data.db.dao.AccRoomDao
import com.example.bookingagent.data.db.dao.RoomDao
import com.example.bookingagent.data.db.entities.AccRoom
import com.example.bookingagent.data.db.entities.RoomEntity
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

	fun addRoom(accId: Int, roomEntity: RoomEntity) =
		roomApi.addRoom(EnvelopeAddChangeRoomRequest(roomEntity.toRequest(accId))).toSealed()

	fun editRoom(roomEntity: RoomEntity) =
		roomApi.editRoom(EnvelopeAddChangeRoomRequest(roomEntity.toRequest())).toSealed()

	fun deleteRoom(id: Int) =
		roomApi.deleteRoom(EnvelopeDeleteRoomRequest(DeleteRoomRequest(id = id.toInt()))).toSealed()

	// endregion NETWORK

	// region DB

	fun addRoomToDB(roomEntity: RoomEntity) =
		Single.just(roomDao.addRoom(roomEntity)).toSealed()

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

	fun updateRoomInDB(roomEntity: RoomEntity) =
		Single.just(roomDao.updateRoom(roomEntity)).toSealed()

	// endregion DB
}