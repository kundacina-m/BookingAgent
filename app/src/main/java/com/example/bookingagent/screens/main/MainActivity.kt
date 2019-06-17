package com.example.bookingagent.screens.main

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import base.BaseActivity
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.activity_main.bottom_navigation

class MainActivity : BaseActivity() {

	private val fragmentsWithNavigationBar = listOf(
		R.id.homeFragment,
		R.id.profileFragment,
		R.id.reservationsFragment,
		R.id.messagesFragment,
		R.id.accommodationsFragment
	)

	private val navCtrl: NavController by lazy {
		Navigation.findNavController(this, R.id.nav_host_fragment)
	}

	override fun getViewLayout(): Int = R.layout.activity_main

	override fun initView() {
		setupBottomNavBar()
		setNavigationListener()
	}

	private fun setupBottomNavBar() {
		bottom_navigation.setupWithNavController(navCtrl)
		bottom_navigation.setOnNavigationItemReselectedListener { }

	}

	private fun setNavigationListener() {
		navCtrl.addOnDestinationChangedListener { _, destination, _ ->
			if (fragmentsWithNavigationBar.contains(destination.id))
				bottom_navigation.visibility = View.VISIBLE
			else bottom_navigation.visibility = View.GONE
		}
	}

	override fun onSupportNavigateUp(): Boolean =
		navCtrl.navigateUp()

}
