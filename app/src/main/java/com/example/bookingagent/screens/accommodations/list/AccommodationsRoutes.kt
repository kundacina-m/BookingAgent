package com.example.bookingagent.screens.accommodations.list

import com.example.bookingagent.R
import com.example.bookingagent.Routes
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class AccommodationsRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

    fun navigateToAddAccommodation() =
        navigationController.route.navigate(R.id.action_accommodationsFragment_to_addAccommodationFragment)

    fun navigateToSelectedItem(accommodation: Accommodation) {

        val args = AccommodationsFragmentDirections.actionAccommodationsFragmentToAccommodationDetailsFragment(
            id = accommodation.id,
            name = accommodation.name,
            description = accommodation.description,
            cancellingFee = accommodation.cancelingFee,
            type = accommodation.type,
            category = accommodation.category,
            rooms = accommodation.rooms.toTypedArray(),
            services = accommodation.services.toTypedArray(),
            rating = accommodation.rating,
            address = accommodation.address
        )

        navigationController.route.navigate(args)

    }
}