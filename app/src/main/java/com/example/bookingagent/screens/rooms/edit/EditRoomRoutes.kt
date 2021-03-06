package com.example.bookingagent.screens.rooms.edit

import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class EditRoomRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToRooms() =
		navigationController.route.navigateUp()
}