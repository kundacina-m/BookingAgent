package com.example.bookingagent.screens.register

import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class RegisterRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToLogin() =
		navigationController.route.navigateUp()
}