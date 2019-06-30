package com.example.bookingagent.screens.messages.thread

import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.NavigationController
import javax.inject.Inject

class MessageThreadRoutes @Inject constructor(val navigationController: NavigationController) : Routes() {

    fun navigateBack() =
        navigationController.route.navigateUp()
}