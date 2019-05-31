package com.example.bookingagent.di.routes

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.bookingagent.R
import javax.inject.Inject

class NavigationControllerImpl @Inject constructor(activity: Activity) :
	NavigationController {

	override var route: NavController = Navigation.findNavController(activity, R.id.nav_host_fragment)

}