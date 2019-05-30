package com.example.bookingagent.utils

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.bookingagent.R
import com.example.bookingagent.screens.main.MainActivity
import javax.inject.Inject

class NavigationController @Inject constructor(mainActivity: MainActivity) {

    lateinit var route: NavController

    init {
        route = mainActivity.findNavController(R.id.nav_host_fragment)
    }
}