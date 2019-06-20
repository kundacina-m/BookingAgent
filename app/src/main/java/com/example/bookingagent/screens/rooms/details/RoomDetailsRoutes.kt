package com.example.bookingagent.screens.rooms.details

import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class RoomDetailsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToEdit(room: Room) =
		RoomDetailsFragmentDirections.actionRoomDetailsFragmentToEditRoomFragment(
			room = room
		).run { navigationController.route.navigate(this) }

	fun navigateToRooms() =
		navigationController.route.navigateUp()

}