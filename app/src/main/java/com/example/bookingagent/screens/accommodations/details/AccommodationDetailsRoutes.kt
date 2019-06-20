package com.example.bookingagent.screens.accommodations.details

import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class AccommodationDetailsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToEdit(accommodation: Accommodation) =
		AccommodationDetailsFragmentDirections.actionAccommodationDetailsFragmentToAccommodationEditFragment(
			id = accommodation.id,
			name = accommodation.name!!,
			description = accommodation.description!!,
			cancellingFee = accommodation.cancellingFee!!,
			type = accommodation.type!!,
			address = accommodation.address,
			rating = accommodation.rating!!,
			services = accommodation.services.toTypedArray(),
			category = accommodation.category!!
		).run { navigationController.route.navigate(this) }

	fun navigateToRooms(accId: Int) =
		AccommodationDetailsFragmentDirections.actionAccommodationDetailsFragmentToRoomsFragment(accId = accId)
			.run { navigationController.route.navigate(this) }

	fun navigateToAccommodations() =
		navigationController.route.navigateUp()
}