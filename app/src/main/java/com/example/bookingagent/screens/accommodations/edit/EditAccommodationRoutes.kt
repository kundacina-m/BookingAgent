package com.example.bookingagent.screens.accommodations.edit

import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class EditAccommodationRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToAccommodations() =
		navigationController.route.navigateUp()
}