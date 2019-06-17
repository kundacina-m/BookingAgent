package com.example.bookingagent.screens.rooms.list

import com.example.bookingagent.R
import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class RoomsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {
	fun navigateToSelectedRoom(room: Room) {
		val args = RoomsFragmentDirections.actionRoomsFragmentToRoomDetailsFragment(
			room = room
		)
		navigationController.route.navigate(args)
	}

	fun navigateToAddRoom() =
		navigationController.route.navigate(R.id.action_roomsFragment_to_addRoomFragment)

}