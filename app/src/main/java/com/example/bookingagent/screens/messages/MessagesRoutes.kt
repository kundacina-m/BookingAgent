package com.example.bookingagent.screens.messages

import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class MessagesRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

	fun navigateToThread(resId: Int) =
		MessagesFragmentDirections.actionMessagesFragmentToMessageThreadFragment(resId = resId).run {
			navigationController.route.navigate(this)
		}
}