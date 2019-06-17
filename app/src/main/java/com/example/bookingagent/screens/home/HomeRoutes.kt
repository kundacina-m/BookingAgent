package com.example.bookingagent.screens.home

import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class HomeRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToLogin() =
		navigationController.route.popBackStack()

}