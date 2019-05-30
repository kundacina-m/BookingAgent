package com.example.bookingagent

import android.app.Activity

class NavigationImpl(activity: Activity) : Navigation {


	private val navController =
		androidx.navigation.Navigation.findNavController(activity, R.id.nav_host_fragment)


	override fun navigateToRegister() {

		navController.navigate(R.id.action_loginFragment_to_registerFragment)
	}

}