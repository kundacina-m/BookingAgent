package com.example.bookingagent.screens.accommodations.details

import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class AccommodationDetailsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToEdit(accommodationEntity: AccommodationEntity) =
		AccommodationDetailsFragmentDirections.actionAccommodationDetailsFragmentToAccommodationEditFragment(
			id = accommodationEntity.id,
			name = accommodationEntity.name!!,
			description = accommodationEntity.description!!,
			cancellingFee = accommodationEntity.cancellingFee!!,
			type = accommodationEntity.type!!,
			address = accommodationEntity.address,
			rating = accommodationEntity.rating!!,
			services = accommodationEntity.services.toTypedArray(),
			category = accommodationEntity.category!!,
			cancellingDays = accommodationEntity.cancellingDays!!
		).run { navigationController.route.navigate(this) }

	fun navigateToRooms(accId: Int) =
		AccommodationDetailsFragmentDirections.actionAccommodationDetailsFragmentToRoomsFragment(accId = accId)
			.run { navigationController.route.navigate(this) }

	fun navigateToAccommodations() =
		navigationController.route.navigateUp()
}