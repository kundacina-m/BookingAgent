package com.example.bookingagent.screens.login

import com.example.bookingagent.R
import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class LoginRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToRegister() =
		navigationController.route.navigate(R.id.action_loginFragment_to_registerFragment)

	fun navigateToHome() =
		navigationController.route.navigate(R.id.action_loginFragment_to_homeFragment)

}