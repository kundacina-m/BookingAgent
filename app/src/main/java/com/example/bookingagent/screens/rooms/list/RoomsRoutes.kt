package com.example.bookingagent.screens.rooms.list

import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class RoomsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToSelectedRoom(accId: Int, room: Room) =
		RoomsFragmentDirections.actionRoomsFragmentToRoomDetailsFragment(
			room = room,
			accId = accId
		).run { navigationController.route.navigate(this) }

	fun navigateToAddRoom(accId: Int) =
		RoomsFragmentDirections.actionRoomsFragmentToAddRoomFragment(
			accId = accId
		).run { navigationController.route.navigate(this) }
}