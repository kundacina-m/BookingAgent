package com.example.bookingagent.screens.accommodations

import com.example.bookingagent.R
import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class AccommodationsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToAddAccommodation() {
		navigationController.route.navigate(R.id.action_accommodationsFragment_to_addAccommodationFragment)
	}
}