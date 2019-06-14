package com.example.bookingagent.screens.accommodations.details

import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class AccommodationDetailsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToEdit(accommodation: AccommodationDetailsFragmentArgs) {
		val args = AccommodationDetailsFragmentDirections.actionAccommodationDetailsFragmentToAccommodationEditFragment(
			id = accommodation.id,
			name = accommodation.name,
			description = accommodation.description,
			cancellingFee = accommodation.cancellingFee,
			type = accommodation.type,
			beds = accommodation.beds,
			address = accommodation.address,
			images = accommodation.images
		)

		navigationController.route.navigate(args)
	}
}