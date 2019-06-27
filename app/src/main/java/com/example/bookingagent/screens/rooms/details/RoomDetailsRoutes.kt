package com.example.bookingagent.screens.rooms.details

import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class RoomDetailsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToEdit(roomEntity: RoomEntity) =
		RoomDetailsFragmentDirections.actionRoomDetailsFragmentToEditRoomFragment(
			roomEntity = roomEntity
		).run { navigationController.route.navigate(this) }

	fun navigateToRooms() =
		navigationController.route.navigateUp()

}