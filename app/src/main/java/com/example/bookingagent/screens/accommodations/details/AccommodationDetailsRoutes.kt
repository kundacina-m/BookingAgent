package com.example.bookingagent.screens.accommodations.details

import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.Room
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
			address = accommodation.address,
			rating = accommodation.rating,
			services = accommodation.services,
			rooms = accommodation.rooms,
			category = accommodation.category
		)

		navigationController.route.navigate(args)
	}

	fun navigateToRooms(rooms: Array<Room>) {
		val args = AccommodationDetailsFragmentDirections.actionAccommodationDetailsFragmentToRoomsFragment(
			rooms = rooms
		)

		navigationController.route.navigate(args)
	}
}